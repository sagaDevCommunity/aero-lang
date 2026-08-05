package com.Aero
/*

--- AUTHORS ---
saga.dev - Creators of Aero
dercode-solutions2025 - Main author of Aero, owner of saga.dev.

--- ABOUT ---
Welcome to the compiler!
Instead of writing two parsers, bytecode, and an AST, I decided to make the tokens compile directly to JS!

--- USAGE ---
The compiler outputs a string of the resulting JS file, and passes it to the main script. The main script then reads the user's code, compiles it to JS, puts the file as _.js in build/.

So, let's get started!

*/
var OUT: String = ""
fun AeroCompileLine(ln: List<Any>) {
    var PTR: Int = 0
    while (PTR < ln.size) {
        if (ln[PTR] == Token.CommentBegin) {
            while (ln[PTR] != Token.CommentEnd && PTR < ln.size) { PTR++; }
            PTR++
            continue
        } else if(ln[PTR] == Token.StringLiteral && PTR < ln.size) {
            PTR++
            OUT += "\"${ln[PTR]}\""
            PTR++
            continue
        } else if(ln[PTR] == Token.CharLiteral && PTR < ln.size) {
            PTR++
            OUT += "'${ln[PTR]}'"
            PTR++
            continue
        } else if (ln[PTR] == Token.Print_Stmt && PTR < ln.size) {
            PTR++
            if (ln[PTR] == Token.Identifier) {
                OUT += "console.log(${ln[PTR]});"
            } else if (ln[PTR] == Token.StringLiteral) {
                PTR++
                OUT += "console.log(\"${ln[PTR]}\");"
            } else if(ln[PTR] == Token.CharLiteral) {
                PTR++
                OUT += "console.log(\'${ln[PTR]}\');"
            }
            PTR++
            continue
        } else if (ln[PTR] == Token.Write_Stmt && PTR < ln.size) {
            PTR++
            if (ln[PTR] == Token.Identifier) {
                OUT += "document.write(${ln[PTR]});"
            } else if (ln[PTR] == Token.StringLiteral) {
                PTR++
                OUT += "document.write(\"${ln[PTR]}\");"
            } else if(ln[PTR] == Token.CharLiteral) {
                PTR++
                OUT += "document.write(\'${ln[PTR]}\');"
            }
            PTR++
            continue
        } else {
            if (PTR < ln.size) {
                PTR++
            } else {
                break
            }
        }
    }
    println("$OUT")
}

fun main() { AeroCompileLine(listOf(Token.CommentBegin, Token.CommentEnd, Token.Print_Stmt, Token.StringLiteral, "Hello World!", Token.Write_Stmt, Token.CharLiteral, 'A'))}