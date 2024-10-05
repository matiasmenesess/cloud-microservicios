const mongoose = require('mongoose');

const reviewSchema = new mongoose.Schema({
  id_libro: {
    type: String,
    required: true
  },
  id_cliente: {
    type: Number,
    required: true
  },
  puntuacion: {
    type: Number,
    required: true
  },
  resena: {
    type: String,
    required: true
  },
  fecha: {
    type: Date,
    default: Date.now
  }
});

const Review = mongoose.model('Review', reviewSchema, 'book_reviews');

module.exports = Review;
