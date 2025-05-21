const express = require('express');
const swaggerJsDoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');
const { DataSource, EntitySchema } = require('typeorm');

const app = express();
const port = 3000;

app.use(express.json());

// Swagger configuration
const swaggerOptions = {
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'Quiz API',
            version: '1.0.0',
            description: 'An API to fetch and validate quiz questions'
        }
    },
    apis: [__filename]
};

const swaggerDocs = swaggerJsDoc(swaggerOptions);
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocs));

// Define the Question entity
const Question = new EntitySchema({
    name: "Question",
    columns: {
        id: { primary: true, type: "int", generated: true },
        text: { type: "text" }
    }
});

// Define the Answer entity
const Answer = new EntitySchema({
    name: "Answer",
    columns: {
        id: { primary: true, type: "int", generated: true },
        questionId: { type: "int" },
        text: { type: "text" },
        isCorrect: { type: "boolean" }
    }
});

// Initialize in-memory SQLite database
const AppDataSource = new DataSource({
    type: "sqlite",
    database: ":memory:",
    synchronize: true,
    entities: [Question, Answer]
});

AppDataSource.initialize().then(async () => {
    const questionRepository = AppDataSource.getRepository("Question");
    const answerRepository = AppDataSource.getRepository("Answer");

    // Prepopulate with some questions and answers
    const questionsWithAnswers = [
        { text: "What is the capital of Japan?", answers: ["Tokyo", "Osaka", "Kyoto", "Nagoya"], correct: "Tokyo" },
        { text: "Who developed the theory of relativity?", answers: ["Isaac Newton", "Albert Einstein", "Galileo Galilei", "Nikola Tesla"], correct: "Albert Einstein" },
        { text: "What is the powerhouse of the cell?", answers: ["Nucleus", "Mitochondria", "Ribosome", "Golgi apparatus"], correct: "Mitochondria" },
        { text: "Which planet is known as the Red Planet?", answers: ["Earth", "Mars", "Jupiter", "Venus"], correct: "Mars" },
        { text: "What is the longest river in the world?", answers: ["Amazon River", "Nile River", "Yangtze River", "Mississippi River"], correct: "Nile River" },
        { text: "What is the boiling point of water in Celsius?", answers: ["90", "100", "110", "120"], correct: "100" },
        { text: "Which gas do plants absorb from the atmosphere?", answers: ["Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"], correct: "Carbon Dioxide" },
        { text: "Who wrote 'Romeo and Juliet'?", answers: ["William Shakespeare", "Jane Austen", "Charles Dickens", "Mark Twain"], correct: "William Shakespeare" },
        { text: "What is the chemical symbol for gold?", answers: ["Ag", "Au", "Pb", "Fe"], correct: "Au" },
        { text: "Which ocean is the largest?", answers: ["Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"], correct: "Pacific Ocean" },
        { text: "What is the capital of France?", answers: ["Paris", "Berlin", "Madrid", "Rome"], correct: "Paris" },
        { text: "Which element has the atomic number 1?", answers: ["Helium", "Oxygen", "Hydrogen", "Carbon"], correct: "Hydrogen" },
        { text: "Who painted the Mona Lisa?", answers: ["Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"], correct: "Leonardo da Vinci" },
        { text: "What is the square root of 64?", answers: ["6", "8", "10", "12"], correct: "8" },
        { text: "Which country is known as the Land of the Rising Sun?", answers: ["China", "Japan", "South Korea", "Thailand"], correct: "Japan" },
        { text: "What is the capital of Canada?", answers: ["Toronto", "Ottawa", "Vancouver", "Montreal"], correct: "Ottawa" },
        { text: "Who discovered gravity?", answers: ["Albert Einstein", "Galileo Galilei", "Isaac Newton", "Nikola Tesla"], correct: "Isaac Newton" },
        { text: "What is the hardest natural substance on Earth?", answers: ["Gold", "Iron", "Diamond", "Quartz"], correct: "Diamond" },
        { text: "How many continents are there?", answers: ["5", "6", "7", "8"], correct: "7" },
        { text: "Which planet is closest to the sun?", answers: ["Venus", "Earth", "Mercury", "Mars"], correct: "Mercury" },
        { text: "Who is known as the father of computers?", answers: ["Alan Turing", "Charles Babbage", "Bill Gates", "Steve Jobs"], correct: "Charles Babbage" },
        { text: "What is the largest organ in the human body?", answers: ["Heart", "Brain", "Liver", "Skin"], correct: "Skin" },
        { text: "What is the currency of the United Kingdom?", answers: ["Euro", "Dollar", "Pound Sterling", "Yen"], correct: "Pound Sterling" },
        { text: "Which metal is liquid at room temperature?", answers: ["Iron", "Mercury", "Aluminum", "Copper"], correct: "Mercury" },
        { text: "What does H2O stand for?", answers: ["Oxygen", "Hydrogen Peroxide", "Water", "Salt"], correct: "Water" },
        { text: "Which planet has the most moons?", answers: ["Jupiter", "Saturn", "Mars", "Neptune"], correct: "Saturn" },
        { text: "What is the national sport of Japan?", answers: ["Baseball", "Sumo Wrestling", "Soccer", "Karate"], correct: "Sumo Wrestling" },
        { text: "Who invented the telephone?", answers: ["Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Guglielmo Marconi"], correct: "Alexander Graham Bell" },
        { text: "Which language has the most native speakers?", answers: ["English", "Spanish", "Mandarin Chinese", "Hindi"], correct: "Mandarin Chinese" },
        { text: "Which country has the most volcanoes?", answers: ["Indonesia", "Japan", "USA", "Italy"], correct: "Indonesia" },
        { text: "Who is the Greek god of war?", answers: ["Zeus", "Ares", "Apollo", "Hermes"], correct: "Ares" },
        { text: "What is the fastest land animal?", answers: ["Cheetah", "Lion", "Horse", "Greyhound"], correct: "Cheetah" },
        { text: "Which gas makes up most of the Earth's atmosphere?", answers: ["Oxygen", "Carbon Dioxide", "Nitrogen", "Helium"], correct: "Nitrogen" },
        { text: "What is the tallest mountain in the world?", answers: ["K2", "Everest", "Kilimanjaro", "Denali"], correct: "Everest" },
        { text: "What year did World War II end?", answers: ["1943", "1945", "1950", "1939"], correct: "1945" },
        { text: "What is the largest desert in the world?", answers: ["Sahara", "Antarctic", "Gobi", "Kalahari"], correct: "Antarctic" },
        { text: "Who was the first person to walk on the moon?", answers: ["Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Michael Collins"], correct: "Neil Armstrong" },
        { text: "How many sides does a hexagon have?", answers: ["4", "5", "6", "7"], correct: "6" },
        { text: "What is the chemical symbol for silver?", answers: ["Ag", "Au", "Si", "Pb"], correct: "Ag" },
        { text: "What is the national flower of Japan?", answers: ["Tulip", "Cherry Blossom", "Rose", "Lily"], correct: "Cherry Blossom" },
        { text: "Which instrument has 88 keys?", answers: ["Guitar", "Violin", "Piano", "Flute"], correct: "Piano" },
        { text: "How many bones are in the adult human body?", answers: ["206", "208", "210", "212"], correct: "206" },
        { text: "Which country is famous for the Great Wall?", answers: ["India", "China", "Russia", "Mongolia"], correct: "China" },
        { text: "Which vitamin is produced when a person is exposed to sunlight?", answers: ["Vitamin A", "Vitamin C", "Vitamin D", "Vitamin E"], correct: "Vitamin D" },
        { text: "What is the main ingredient in traditional Japanese miso soup?", answers: ["Tofu", "Soybean Paste", "Seaweed", "Rice"], correct: "Soybean Paste" },
        { text: "What is the smallest country in the world?", answers: ["Monaco", "Malta", "Vatican City", "San Marino"], correct: "Vatican City" }
    ];
    
    for (let i = 0; i < 5; i++) {
        for (const item of questionsWithAnswers) {
            const question = await questionRepository.save({ text: item.text });
            for (const answer of item.answers) {
                await answerRepository.save({
                    questionId: question.id,
                    text: answer,
                    isCorrect: answer === item.correct
                });
            }
        }
    }

    /**
     * @swagger
     * /quiz:
     *   get:
     *     summary: "Get 10 random questions with multiple answers"
     *     responses:
     *       200:
     *         description: "Returns an array of 10 random questions with their answers."
     *         content:
     *           application/json:
     *             schema:
     *               type: array
     *               items:
     *                 type: object
     *                 properties:
     *                   id:
     *                     type: integer
     *                   text:
     *                     type: string
     *                   answers:
     *                     type: array
     *                     items:
     *                       type: object
     *                       properties:
     *                         id:
     *                           type: integer
     *                         text:
     *                           type: string
     */
    app.get('/quiz', async (req, res) => {
        const questions = await questionRepository.find();
        const response = await Promise.all(questions.map(async q => {
            const answers = await answerRepository.find({ where: { questionId: q.id } });
            return { id: q.id, text: q.text, answers: answers.map(a => ({ id: a.id, text: a.text })) };
        }));
        res.json(response);
    });

    /**
     * @swagger
     * /validate:
     *   post:
     *     summary: "Validate quiz answers"
     *     requestBody:
     *       required: true
     *       content:
     *         application/json:
     *           schema:
     *             type: array
     *             items:
     *               type: object
     *               properties:
     *                 questionId:
     *                   type: integer
     *                 answerId:
     *                   type: integer
     *     responses:
     *       200:
     *         description: "Returns validation results"
     *         content:
     *           application/json:
     *             schema:
     *               type: object
     *               properties:
     *                 correct:
     *                   type: integer
     *                 total:
     *                   type: integer
     */
     /**
     * @swagger
     * /validate:
     *   post:
     *     summary: "Validate quiz answers and return detailed feedback"
     *     requestBody:
     *       required: true
     *       content:
     *         application/json:
     *           schema:
     *             type: array
     *             items:
     *               type: object
     *               properties:
     *                 questionId:
     *                   type: integer
     *                 answerId:
     *                   type: integer
     *     responses:
     *       200:
     *         description: "Returns validation results and details for each question"
     *         content:
     *           application/json:
     *             schema:
     *               type: object
     *               properties:
     *                 correct:
     *                   type: integer
     *                 total:
     *                   type: integer
     *                 details:
     *                   type: array
     *                   items:
     *                     type: object
     *                     properties:
     *                       questionId:
     *                         type: integer
     *                       correct:
     *                         type: boolean
     */
     app.post('/validate', async (req, res) => {
        const userAnswers = req.body;
        let correct = 0;
        let details = [];

        for (const userAnswer of userAnswers) {
            const answer = await answerRepository.findOneBy({ id: userAnswer.answerId });
            const isCorrect = answer ? answer.isCorrect : false;
            if (isCorrect) correct++;
            
            details.push({
                questionId: userAnswer.questionId,
                correct: isCorrect
            });
        }

        res.json({ correct, total: userAnswers.length, details });
    });

    app.listen(port, () => {
        console.log(`Server running at http://localhost:${port}`);
        console.log(`Swagger docs available at http://localhost:${port}/api-docs`);
    });
}).catch(error => console.log(error));
