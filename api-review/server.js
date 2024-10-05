const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const reviewRoutes = require('./routes/reviewRoutes');
const swaggerSetup = require('./config/swagger');

const app = express();
const PORT = 3000;
const mongoURI = 'mongodb://52.55.200.165:27017/reviews';

app.use(bodyParser.json());
app.use('/reviews', reviewRoutes);

swaggerSetup(app);

mongoose.connect(mongoURI, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => console.log('Connected to MongoDB'))
  .catch(err => console.log(err));

app.get('/test', (req, res) => {
  res.json({ message: '¡Funciona!' });
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
