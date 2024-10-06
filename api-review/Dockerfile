# Usa la imagen oficial de Node.js como base
FROM node:16

# Establece el directorio de trabajo en el contenedor
WORKDIR /usr/src/app

# Copia el archivo package.json y package-lock.json al contenedor
COPY package*.json ./

# Instala las dependencias de la aplicación
RUN npm install

# Copia el resto de la aplicación en el contenedor
COPY . .

# Expone el puerto en el que corre la aplicación (en este caso, el puerto 3000)
EXPOSE 3000

# Comando para iniciar la aplicación
CMD ["npm", "start"]
