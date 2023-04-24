# We base our image on the official Node.js image
# and use the current, slim version.
FROM node:current-slim
# Set working directory. All operations are relative to this path.
WORKDIR /usr/src/app
# We only have one file. Copy it to the working directory.
COPY example.js .
# Execute the node command against our source file.
CMD [ "node", "./example.js" ]