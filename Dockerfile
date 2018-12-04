FROM openjdk:8 as builder

RUN set -ex \
	&& DEBIAN_FRONTEND=noninteractive apt-get update \
	&& DEBIAN_FRONTEND=noninteractive apt-get install -y gnupg dirmngr apt-transport-https \
	&& apt-key adv --fetch-keys https://deb.nodesource.com/gpgkey/nodesource.gpg.key \
	&& echo 'deb https://deb.nodesource.com/node_8.x stretch main' > /etc/apt/sources.list.d/nodesource.list \
	&& apt-key adv --fetch-keys https://dl.yarnpkg.com/debian/pubkey.gpg \
	&& echo 'deb https://dl.yarnpkg.com/debian/ stable main' > /etc/apt/sources.list.d/yarn.list \
	&& DEBIAN_FRONTEND=noninteractive apt-get update \
	&& DEBIAN_FRONTEND=noninteractive apt-get install -y \
		nodejs yarn \
	&& rm -rf /var/lib/apt/lists/*

RUN mkdir -p ~/.m2

RUN git clone https://github.com/minhtuanvu/erp-mvn-cache.git ~/.m2/repository

ENV APP_SRC_CACHE /app/src_cache

RUN mkdir -p $APP_SRC_CACHE

WORKDIR $APP_SRC_CACHE

RUN \
  set -ex && \
  git clone https://github.com/axelor/abs-webapp.git axelor-erp && \
  sed -e 's|git@github.com:|https://github.com/|' -i axelor-erp/.gitmodules && \
  cd axelor-erp && \
  git checkout tags/v5.0.6 && \
  git submodule sync && \
  git submodule init && \
  git submodule update && \
  git submodule foreach git checkout master && \
  git submodule foreach git pull origin master && \
  sed -e 's|^application.theme.*|application.theme = modern|g' -i src/main/resources/application.properties && \
  ./gradlew --no-daemon -x test npm-build build && \
  cd && \
  rm $APP_SRC_CACHE -rf

ENV APP_SRC /app/src

RUN mkdir -p $APP_SRC

WORKDIR $APP_SRC

# copy sources
COPY . $APP_SRC

RUN ls -lah $APP_SRC

# build sources
RUN set -ex \
	&& cd $APP_SRC \
	&& ./gradlew --no-daemon -x test npm-build build

FROM tomcat:8.5-slim
LABEL maintainer="TuanVM <vuminhtuan@live.com>"

COPY --from=builder /app/src/build/libs/axelor-erp-*.war $CATALINA_BASE/webapps/ROOT.war
CMD ["start"]
