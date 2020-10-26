package com.example.quizapp

object Constants{

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        //Question 1
        val que1 = Question(
            1,
            "What's the name of the band/artist?",
            R.drawable.img_fletcher,
            "Britney Spears",
            "Fletcher",
            "Lauren Jauregui",
            "Pink",
            2
        )

        questionsList.add(que1)

        //Question 2
        val que2 = Question(
            2,
            "What's the name of the band/artist?",
            R.drawable.img_glass_animals,
            "Sum41",
            "The Strokes",
            "Glass Animals",
            "Blink 182",
            3
        )

        questionsList.add(que2)

        //Question 3
        val que3 = Question(
            3,
            "What's the name of the band/artist?",
            R.drawable.img_gossip,
            "Gossip",
            "Franz Ferdinand",
            "Tonight Alive",
            "Metric",
            1
        )

        questionsList.add(que3)

        //Question 4
        val que4 = Question(
            4,
            "What's the name of the band/artist?",
            R.drawable.img_grimes,
            "Grimes",
            "Lady Gaga",
            "Camila Cabello",
            "Ciara",
            1
        )

        questionsList.add(que4)

        //Question 5
        val que5 = Question(
            5,
            "What's the name of the band/artist?",
            R.drawable.img_king_princess,
            "Halsey",
            "King Princess",
            "Katy Perry",
            "Dua Lipa",
            2
        )

        questionsList.add(que5)

        //Question 6
        val que6 = Question(
            6,
            "What's the name of the band/artist?",
            R.drawable.img_paramore,
            "Blondie",
            "Birdy",
            "Lorde",
            "Paramore",
            4
        )

        questionsList.add(que6)

        //Question 7
        val que7 = Question(
            7,
            "What's the name of the band/artist?",
            R.drawable.img_pvris,
            "No Doubt",
            "Korn",
            "PVRIS",
            "Muse",
            3
        )

        questionsList.add(que7)

        //Question 8
        val que8 = Question(
            8,
            "What's the name of the band/artist?",
            R.drawable.img_scissor_sisters,
            "Scissor Sisters",
            "Bee Gees",
            "Madonna",
            "Tame Impala",
            1
        )

        questionsList.add(que8)

        //Question 9
        val que9 = Question(
            9,
            "What's the name of the band/artist?",
            R.drawable.img_sofi_tukker,
            "Greta Van Fleet",
            "Nine Inch Nails",
            "Sofi Tukker",
            "Roxxete",
            3
        )

        questionsList.add(que9)

        //Question 10
        val que10 = Question(
            10,
            "What's the name of the band/artist?",
            R.drawable.img_the_aces,
            "The Aces",
            "Years & Years",
            "Massive Atack",
            "Goldfrapp",
            1
        )

        questionsList.add(que10)

        return questionsList
    }
}