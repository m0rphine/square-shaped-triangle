package com.example.square_shaped_triangle.test

import com.example.square_shaped_triangle.R

class GenerateData {

    fun generateUsers(): ArrayList<UserTest> {
        val users = ArrayList<UserTest>()
        users.add(UserTest("Kail", R.drawable.ic_boy))
        users.add(UserTest("Nivarra", R.drawable.ic_woman))
        users.add(UserTest("Rose", R.drawable.ic_woman1))
        users.add(UserTest("Paul", R.drawable.ic_man1))
        users.add(UserTest("Phillip", R.drawable.ic_boy1))
        users.add(UserTest("Angelina", R.drawable.ic_woman2))
        return users
    }

//    fun generateGames(): ArrayList<GameTest>{
//        val games = ArrayList<GameTest>()
//        games.add(GameTest("Catan", "Catan","1998", "Catan Universe", "3-12", 15, "10+"))
//        games.add(GameTest("", "Pandemic", "2008", "Z-Man Games", "2-4", 45, "8+"))
//        games.add(GameTest("", "Spirit Islands", "2016", "Greater Than Games", "1-4", 90, "13+"))
//        games.add(GameTest("","Gloomhaven", "2017", "Cephalofair Games", "1-4", 60, "12+"))
//        games.add(GameTest("", "Azul", "2017", "Next Move Games", "2-4", 30, "8+"))
//        games.add(GameTest("", "Scythe", "2016", "Stonemaier Games", "1-5", 90, "14+"))
//        games.add(GameTest())
//        games.add(GameTest())
//        games.add(GameTest())
//        games.add(GameTest())
//        return games
//    }
//
//    fun generateEvents(): ArrayList<EventTest>{
//        val events = ArrayList<EventTest>()
//        events.add(EventTest())
//        events.add(EventTest())
//        events.add(EventTest())
//        events.add(EventTest())
//        events.add(EventTest())
//        events.add(EventTest())
//        events.add(EventTest())
//        events.add(EventTest())
//    }

}