package machine

class CoffeeMachine(var water: Int = 400,
                    var milk: Int = 540,
                    var coffeeBeans: Int = 120,
                    var cups: Int = 9,
                    var money: Int = 550
)

fun showState(machine: CoffeeMachine) {
    println("The coffee machine has:")
    println("${machine.water} of water")
    println("${machine.milk} of milk")
    println("${machine.coffeeBeans} of coffee beans")
    println("${machine.cups} of disposable cups")
    println("${machine.money} of money")
    println("")
}

fun takeMoney(machine: CoffeeMachine) {
    println("I gave you \$${machine.money}")
    println("")
    machine.money = 0
}

fun fillMachine(machine: CoffeeMachine) {
    println("Write how many ml of water do you want to add:")
    val water: Int = readLine()!!.toInt()
    println("Write how many ml of milk do you want to add:")
    val milk: Int = readLine()!!.toInt()
    println("Write how many grams of coffee beans do you want to add:")
    val grams: Int = readLine()!!.toInt()
    println("Write how many disposable cups of coffee do you want to add:")
    val cups: Int = readLine()!!.toInt()

    machine.water += water
    machine.milk += milk
    machine.coffeeBeans += grams
    machine.cups += cups
}

fun takeResource(machine: CoffeeMachine, water: Int, milk: Int = 0, grams: Int = 0): Boolean {
    if (machine.water >= water) {
        machine.water -= water
    } else {
        println("Sorry, not enough water!\n")
        return false
    }

    if (machine.milk >= milk) {
        machine.milk -= milk
    } else {
        println("Sorry, not enough milk!\n")
        return false
    }

    if (machine.coffeeBeans >= grams) {
        machine.coffeeBeans -= grams
    } else {
        println("Sorry, not enough coffee beans!\n")
        return false
    }

    if (machine.cups >= 1) {
        machine.cups -= 1
    } else {
        println("Sorry, not enough cups!\n")
        return false
    }

    return true
}

fun prepareEspresso(machine: CoffeeMachine) {
    val water = 250
    val grams = 16
    val milk = 0

    if (takeResource(machine, water, milk, grams)) {
        machine.money += 4
    }
}

fun prepareLatte(machine: CoffeeMachine) {
    val water = 350
    val milk = 75
    val grams = 20

    if (takeResource(machine, water, milk, grams)) {
        machine.money += 7
    }
}

fun prepareCappuccino(machine: CoffeeMachine) {
    val water = 200
    val milk = 100
    val grams = 12

    if (takeResource(machine, water, milk, grams)) {
        machine.money += 6
    }
}

fun buyCoffee(machine: CoffeeMachine) {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    val option: String = readLine()!!

    when (option) {
        "1" -> prepareEspresso(machine)
        "2" -> prepareLatte(machine)
        "3" -> prepareCappuccino(machine)
    }
}

fun main() {
    val machine: CoffeeMachine = CoffeeMachine()

    do {
        println("Write action (buy, fill, take, remaining, exit):")
        var action: String = readLine()!!

        when (action) {
            "fill" -> fillMachine(machine)
            "buy" -> buyCoffee(machine)
            "take" -> takeMoney(machine)
            "remaining" -> showState(machine)
            "exit" -> break
        }
    } while (action != "exit")
}