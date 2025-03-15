# Step 1: Build the React app
FROM node:18-alpine as build

WORKDIR /app

# Copy only the necessary files first to install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the rest of the application files
COPY . .

# Build the React app
RUN npm run build

# Step 2: Serve the React app with Nginx
FROM nginx:alpine

# Copy the built files from the previous stage to Nginx's default serving directory
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80 for the application
EXPOSE 80

# Run Nginx in the foreground (important to keep the container running)
CMD ["nginx", "-g", "daemon off;"]
