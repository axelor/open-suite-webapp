FROM openjdk:8 as builder

RUN set -ex \
	&& DEBIAN_FRONTEND=noninteractive apt-get update \
	&& DEBIAN_FRONTEND=noninteractive apt-get install -y gnupg dirmngr apt-transport-https \
	&& apt-key adv --batch --fetch-keys https://deb.nodesource.com/gpgkey/nodesource.gpg.key \
	&& echo 'deb https://deb.nodesource.com/node_8.x stretch main' > /etc/apt/sources.list.d/nodesource.list \
	&& apt-key adv --batch --fetch-keys https://dl.yarnpkg.com/debian/pubkey.gpg \
	&& echo 'deb https://dl.yarnpkg.com/debian/ stable main' > /etc/apt/sources.list.d/yarn.list \
	&& DEBIAN_FRONTEND=noninteractive apt-get update \
	&& DEBIAN_FRONTEND=noninteractive apt-get install -y \
		git-core nodejs yarn \
	&& rm -rf /var/lib/apt/lists/*

RUN mkdir -p /app/src
WORKDIR /app/src

# copy sources
COPY . /app/src

# build sources
RUN \
  set -ex && \
  cd /app/src && \
  ./gradlew --no-daemon -x test npm-build build

FROM tomcat:8.5-slim
LABEL maintainer="TuanVM <vuminhtuan@live.com>"

COPY --from=builder /app/src/build/libs/axelor-erp-*.war $CATALINA_BASE/webapps/ROOT.war
CMD ["start"]
