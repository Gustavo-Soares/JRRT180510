//4, 59 -> 4, 59  replaceAll == true, removeDeclaration == true
package p;

enum Letter { A, B, C { } }

class EnumRef {
    Letter l= Letter.A;
}
