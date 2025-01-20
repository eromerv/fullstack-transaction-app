# Stage 1: Build
FROM node:22.12.0 AS build

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application source code
COPY . .

# Copy the environment file (if required)
COPY .env .env

# Build the application
ARG VITE_PORT=3000
ENV VITE_PORT=$VITE_PORT
RUN npm run build

# Stage 2: Serve
FROM nginx:stable-alpine

# Copy built files from the build stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose the custom Vite app port
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
