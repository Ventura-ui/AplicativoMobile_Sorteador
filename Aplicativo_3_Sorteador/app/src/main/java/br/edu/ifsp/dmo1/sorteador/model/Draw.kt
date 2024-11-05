package br.edu.ifsp.dmo1.sorteador.model

class Draw(private val border: Int = 0) {

    private lateinit var strategy: SorteioStrategy
    private val history = ArrayList<Int>()

    init {
        if(border == 0)
            strategy = DefaultLimit
        else
            strategy = DefinedLimit(border)
    }

    fun getNumber(): Int{
        if(!temMaisNumeros()){
            throw IllegalStateException("Todos os números no intervalo escolhido foram sorteados!")
        }

        var number: Int

        do{
            number = strategy.nextNumber()
        }while (history.contains(number))

        history.add(number)
        return number
    }

    fun getHistory() = ArrayList(history)

    fun getLowBorder() = strategy.getLowBorder()

    fun getHighBorder() = strategy.getHighBorder()

    private fun temMaisNumeros(): Boolean{
        return history.size < (strategy.getHighBorder() - strategy.getLowBorder() + 1)
    }
}