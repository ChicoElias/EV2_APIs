package cl.duoc.ev2apis.domain.validation

object FormValidator {
    fun validateEmail(email: String): String? =
        when {
            email.isBlank() -> "Email requerido"
            !email.contains("@") || !email.contains(".") -> "Email inválido"
            else -> null
        }

    fun validatePassword(pass: String, minLen: Int = 6, requireUpper: Boolean = true): String? =
        when {
            pass.length < minLen -> "Mínimo $minLen caracteres"
            !pass.any { it.isDigit() } -> "Debe incluir un número"
            requireUpper && !pass.any { it.isUpperCase() } -> "Debe incluir una mayúscula"
            else -> null
        }

    fun canSubmit(email: String, pass: String, accepted: Boolean, minLen: Int = 6, requireUpper: Boolean = true): Boolean =
        validateEmail(email) == null && validatePassword(pass, minLen, requireUpper) == null && accepted
}
