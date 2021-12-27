enum class ChatColors(val char: Char, val color: String, vararg val names: String) {
    BLACK('0', "#000000", "black"),
    DARK_BLUE('1', "#0000AA", "dark blue", "blue"),
    DARK_GREEN('2', "#00AA00", "green", "dark green"),
    DARK_AQUA('3', "#00AAAA", "cyan", "aqua", "dark cyan", "dark aqua", "dark turquoise", "dark turquois"),
    DARK_RED('4', "#AA0000", "red", "dark red"),
    DARK_PURPLE('5', "#AA00AA", "purple", "dark purple"),
    GOLD('6', "#FFAA00", "gold", "orange", "dark yellow"),
    GRAY('7', "#AAAAAA", "grey", "gray", "light grey", "light gray", "silver"),
    DARK_GRAY('8', "#555555", "dark grey", "dark gray"),
    BLUE('9', "#5555FF", "light blue", "indigo", "brown"),
    GREEN('a', "#55FF55", "green", "light green", "lime", "lime green"),
    AQUA('b', "#55FFFF", "light cyan", "light aqua", "turquoise", "turquois", "light blue"),
    RED('c', "#FF5555", "pink", "light red"),
    LIGHT_PURPLE('d', "#FF55FF", "magenta", "light purple"),
    YELLOW('e', "#FFFF55", "yellow", "light yellow"),
    WHITE('f', "#FFFFFF", "white"),
    RESET('r', "#FFFFFF", "reset");
}