const express = require('express');
const Review = require('../models/reviewModel');
const router = express.Router();

// Obtener todas las reseñas
router.get('/', async (req, res) => {
  try {
    const reviews = await Review.find();  
    res.json(reviews);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Obtener reseñas por ID de cliente
router.get('/cliente/:id_cliente', async (req, res) => {
  try {
    const reviews = await Review.find({ id_cliente: req.params.id_cliente });
    if (reviews.length === 0) return res.status(404).json({ message: 'No se encontraron reseñas para este cliente' });
    res.json(reviews);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Obtener reseñas por ID de libro
router.get('/libro/:id_libro', async (req, res) => {
  try {
    const reviews = await Review.find({ id_libro: req.params.id_libro });
    if (reviews.length === 0) return res.status(404).json({ message: 'No se encontraron reseñas para este libro' });
    res.json(reviews);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Crear una nueva reseña
router.post('/', async (req, res) => {
  const review = new Review({
    id_libro: req.body.id_libro,
    id_cliente: req.body.id_cliente,
    puntuacion: req.body.puntuacion,
    resena: req.body.resena,
    fecha: req.body.fecha
  });

  try {
    const newReview = await review.save();
    res.status(201).json(newReview);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Actualizar una reseña
router.put('/:id', async (req, res) => {
  try {
    const updatedReview = await Review.findByIdAndUpdate(req.params.id, req.body, { new: true });
    if (!updatedReview) return res.status(404).json({ message: 'Review not found' });
    res.json(updatedReview);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Eliminar una reseña
router.delete('/:id', async (req, res) => {
  try {
    const deletedReview = await Review.findByIdAndDelete(req.params.id);
    if (!deletedReview) return res.status(404).json({ message: 'Review not found' });
    res.json({ message: 'Review deleted' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

module.exports = router;
