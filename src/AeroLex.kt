package com.Aero

/*

Welcome to the lexer. Here are some features of it:

1. It is Maximal-Munch based.
2. It uses an enum class to make a custom type - that being "Token". Usage: (var | val) (name): Token = Token.(any item of Token).
3. It uses a single list, and when a null terminator at the end of the line is found, it adds a Token.newline to the list.

Functions:

LexLine() - Lexes a line, appends the tokens and newline to main list
LexProgram() - Lexes the entire program. Uses LexLine() on each line.
LexTest() - Tests the lexer.

Well, without further ado, let's get started.

Update Log:

Sunday, May 31st, 2026: Added 6 Token types and the main list, initialized but haven't started on LexLine()
Monday, June 1st, 2026: Updated the Token types to have 15 types.
Friday, June 12th, 2026: Switched LexMain<Int> to LexMain<Any>. Fixed the lexer to handle numbers. periods, and whole numbers (ex. "[Number, 1, 2]")
Thursday, July 30th, 2026: Finished up by supporting both string literals and char literals.
Friday, July 31st, 2026: Finished the lexer by adding a double equals sign (==). Also added semicolon support and a testing function - LexTest().)
Sunday, August 2nd, 2026: Finished the lexer (again). First upload to GitHub.
*/

enum class Token {
    StringLiteral,
    Number,
    CommentBegin,
    CommentEnd,
    Comma,
    RightParen,
    LeftParen,
    RightSBracket,
    LeftSBracket,
    RightCBracket,
    LeftCBracket,
    Equals,
    GThan,
    LThan,
    GThanOrEqTo,
    LThanOrEqTo,
    DoubleAmper,
    DoublePipe,
    NotEquals,
    PlusEq,
    MinusEq,
    TimesEq,
    DivEq,
    Plus,
    Minus,
    Times,
    Divide,
    Modulus,
    ModulusEq,
    PlusPlus,
    MinusMinus,
    TimesTimes,
    ModMod,
    CharLiteral,
    Period,
    DoubleEquals,
    Semicolon,
    PrintStmt,
    InputStmt,
    IfStmt,
    VarDecl,
    FuncDecl,
    ExclamationMark,
    Colon,
    RightArrow
}

var LexMain = mutableListOf<Any>()
var LexInts = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

fun LexLine(ln: String) {
    var Pointer: Int = 0
    var Line: List<Char> = ln.toList()

    while (Pointer < Line.size) {
        if (Pointer + 2 <= Line.size) {
            if (Line.subList(Pointer, Pointer + 2).joinToString("") == "/*") {
                LexMain.add(Token.CommentBegin)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "*/") {
                LexMain.add(Token.CommentEnd)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "+=") {
                LexMain.add(Token.PlusEq)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "-=") {
                LexMain.add(Token.MinusEq)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "*=") {
                LexMain.add(Token.TimesEq)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "/=") {
                LexMain.add(Token.DivEq)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "&&") {
                LexMain.add(Token.DoubleAmper)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "||") {
                LexMain.add(Token.DoublePipe)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "<=") {
                LexMain.add(Token.LThanOrEqTo)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == ">=") {
                LexMain.add(Token.GThanOrEqTo)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "%=") {
                LexMain.add(Token.ModulusEq)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "!=") {
                LexMain.add(Token.NotEquals)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "++") {
                LexMain.add(Token.PlusPlus)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "--") {
                LexMain.add(Token.MinusMinus)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "**") {
                LexMain.add(Token.TimesTimes)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "%%") {
                LexMain.add(Token.ModMod)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "==") {
                LexMain.add(Token.DoubleEquals)
                Pointer += 2
                continue
            } else if (Line.subList(Pointer, Pointer + 2).joinToString("") == "->") {
                LexMain.add(Token.RightArrow)
                Pointer += 2
                continue
            }
        }

        if (Pointer + 1 <= Line.size) {
            if (Line[Pointer] == ',') {
                LexMain.add(Token.Comma)
                Pointer += 1
                continue
            } else if (Line[Pointer] == ')') {
                LexMain.add(Token.RightParen)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '(') {
                LexMain.add(Token.LeftParen)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '/') {
                LexMain.add(Token.Divide)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '+') {
                LexMain.add(Token.Plus)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '-') {
                LexMain.add(Token.Minus)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '*') {
                LexMain.add(Token.Times)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '[') {
                LexMain.add(Token.LeftSBracket)
                Pointer += 1
                continue
            } else if (Line[Pointer] == ']') {
                LexMain.add(Token.RightSBracket)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '{') {
                LexMain.add(Token.LeftCBracket)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '}') {
                LexMain.add(Token.RightCBracket)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '<') {
                LexMain.add(Token.LThan)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '>') {
                LexMain.add(Token.GThan)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '=') {
                LexMain.add(Token.Equals)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '%') {
                LexMain.add(Token.Modulus)
                Pointer += 1
                continue
            } else if (Line[Pointer] == '"') {
                LexMain.add(Token.StringLiteral)
                Pointer += 1
                var Temp: String = ""
                while (Pointer < Line.size && Line[Pointer] != '"') {
                    Temp += Line[Pointer]
                    Pointer += 1
                }
                LexMain.add(Temp)
                if (Pointer < Line.size && Line[Pointer] == '"') {
                    Pointer += 1
                }
                continue
            } else if (Line[Pointer] == '\'') {
                LexMain.add(Token.CharLiteral)
                Pointer += 1
                var Temp: String = ""
                while (Pointer < Line.size && Line[Pointer] != '\'') {
                    Temp += Line[Pointer]
                    Pointer += 1
                }
                LexMain.add(Temp)
                if (Pointer < Line.size && Line[Pointer] == '\'') {
                    Pointer += 1
                }
                continue
            } else if (Line[Pointer] == '.') {
                LexMain.add(Token.Period)
                Pointer += 1
                continue
            } else if (Line[Pointer] == ';') {
                LexMain.add(Token.Semicolon)
                Pointer += 1
                continue
            } else if (Line[Pointer].digitToIntOrNull() in LexInts) {
                LexMain.add(Token.Number)
                while (Pointer < Line.size && Line[Pointer].digitToIntOrNull() in LexInts) {
                    LexMain.add(Line[Pointer])
                    Pointer += 1
                }
                continue
            } else if(Line[Pointer] == ' ' || Line[Pointer] == '\n') {
                Pointer += 1
                continue
            } else {
                LexMain.add(Line[Pointer])
                Pointer += 1
                continue
            }
        }
    }
}

fun LexTest() {
    print("[TESTING LEXER]\n\n{")
    LexLine("""/* */ , ) ( ] [ } { = < > <= >= && || != += -= *= /= %= + - * / % ; "Hello World!" 'A' 1 2 3 4 5 6 7 8 9 0 . 123 3.14 ++ -- ** %% 1 == -> 123""")
    for (i in LexMain) {
        print("Token.$i, ")
    }
    println("}")
}
// main() is temporary until I finish Aero
fun main() { LexTest() }
