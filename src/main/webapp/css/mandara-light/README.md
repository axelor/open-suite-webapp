# kapsiki-styleguide

The 7-1 repository for Kapsiki ERP app based on Axelor.


This repository is using the [7-1 architecture pattern](https://sass-guidelin.es/#architecture) and sticking to [Sass Guidelines](https://sass-guidelin.es) writing conventions.

Each folder of this repository has its own `README.md` file to explain the purpose and add extra information. Be sure to browse the repository to see how it works.

## Start the repository in watch mode

run the following script:

```
npm run watch:scss
```

## Build
 
The resulting build is located in the `dist` folder, the file name is `mandara.css`

To use the build remotely, you have to:

- install the [Live Server](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer) extension.
- open the `mandara.css` file then, launch the Live Server following the extension's instructions.

## Using the indented syntax

### Sass conversion

This repository does not provide a `.sass` version as it would be painful to maintain both versions without an appropriate build process. However, it is very easy to convert this repository to Sass indented syntax.

Clone it, head into the repository and then run:

```
sass-convert -F scss -T sass -i -R ./  && find . -iname “*.scss” -exec bash -c 'mv "$0" “${0%\.scss}.sass"' {} \;
```

### Use with Sass

When using `sass` - in order to build that repository, one needs to:

- install `sass` if not yet installed:

```bash
npm install -g sass
```

- run build command from command line:

```bash
sass stylesheets/main.scss dist/main.css
```
