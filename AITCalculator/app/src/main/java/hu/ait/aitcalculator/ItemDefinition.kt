package hu.ait.aitcalculator

open class Item(price: Int) {
    open fun calculatePrice() {}
    fun load() {}
}

class SpecialItem(price : Int) : Item(price) {
    final override fun calculatePrice() {}
}

fun demo(){}