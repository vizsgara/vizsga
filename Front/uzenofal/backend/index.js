// backend/index.js

const express = require('express');
const cors = require('cors');
const swaggerUi = require('swagger-ui-express');
const swaggerJsdoc = require('swagger-jsdoc');

const app = express();
const port = 3333;

app.use(cors());
app.use(express.json());

let messages = [
  { id: 1, text: 'Ez az első üzenet!', likes: 2 },
  { id: 2, text: 'Nagyon tetszik ez az alkalmazás.', likes: 5 },
  { id: 3, text: 'Hasznos funkciók, egyszerű kezelhetőség.', likes: 3 }
];
let currentId = 4;

// Swagger beállítás
const swaggerSpec = swaggerJsdoc({
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Üzenőfal API',
      version: '1.0.0',
    },
  },
  apis: ['./index.js'],
});

/**
 * @swagger
 * /api/messages:
 *   get:
 *     summary: Az összes üzenet lekérdezése
 *     responses:
 *       200:
 *         description: Sikeres lekérdezés
 */
app.get('/api/messages', (req, res) => {
  res.json(messages);
});

/**
 * @swagger
 * /api/messages:
 *   post:
 *     summary: Új üzenet hozzáadása
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               text:
 *                 type: string
 *     responses:
 *       200:
 *         description: Sikeres hozzáadás
 */
app.post('/api/messages', (req, res) => {
  const { text } = req.body;
  if (!text || text.trim() === '') {
    return res.status(400).json({ error: 'Üres üzenetet nem lehet beküldeni.' });
  }
  const newMessage = { id: currentId++, text: text.trim(), likes: 0 };
  messages.push(newMessage);
  res.json(messages);
});

/**
 * @swagger
 * /api/messages/{id}/like:
 *   post:
 *     summary: Like növelése adott üzenetnél
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *     responses:
 *       200:
 *         description: Sikeres like
 */
app.post('/api/messages/:id/like', (req, res) => {
  const id = parseInt(req.params.id);
  const message = messages.find(m => m.id === id);
  if (!message) {
    return res.status(404).json({ error: 'Nem található ilyen üzenet.' });
  }
  message.likes++;
  res.json(messages);
});

app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));

app.listen(port, () => {
  console.log(`Backend fut: http://localhost:${port}`);
});