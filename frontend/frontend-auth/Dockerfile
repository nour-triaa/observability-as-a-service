# Étape 1 : builder l'application
FROM node:22.12-alpine AS builder

WORKDIR /app

# Copier package.json et package-lock.json
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Copier le code source
COPY . .

# Construire l'application pour production
RUN npm run build

# Étape 2 : servir avec un serveur léger (nginx)
FROM nginx:alpine

# Copier le build dans nginx
COPY --from=builder /app/dist /usr/share/nginx/html

# Copier configuration nginx (optionnel)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
