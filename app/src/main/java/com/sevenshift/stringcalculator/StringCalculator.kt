package com.sevenshift.stringcalculator

class StringCalculator {
    fun add(numbers: String): Int {
        if (numbers.isEmpty()) {
            return 0
        }

        val separators = getSeparators(numbers)
        val cleanedInput = cleanInput(numbers)
        val extractedNumbers = cleanedInput.split(Regex("""[$separators]"""))

        // calculate sum, and throw exception if negatives exist
        var sum = 0
        var negatives = ""
        extractedNumbers.forEach { numberString ->
            val trimmedNumberString = numberString.trim()
            val number = if (trimmedNumberString.isNotEmpty()) trimmedNumberString.toInt() else 0

            if (number < 1000) {
                if (number < 0) {
                    negatives += "$number, "
                }

                sum += number
            }
        }

        if (negatives.isNotEmpty()) {
            throw IllegalArgumentException("Negatives not allowed: $negatives")
        }

        return sum
    }

    private fun cleanInput(numbers: String): String {
        // remove control code
        return if (numbers.startsWith("//")) {
            // first item is control code
            val separatedNumbers = numbers.split("\n")

            // removes first item
            separatedNumbers.subList(1, separatedNumbers.size).joinToString()
        } else {
            numbers
        }

    }

    private fun getSeparators(numbers: String): String {
        return if (numbers.startsWith("//")) {
            // first item is control code
            val controlCode = numbers.split("\n")[0]
            return controlCode.replace("//", "")

        } else {
            ","
        }
    }
}