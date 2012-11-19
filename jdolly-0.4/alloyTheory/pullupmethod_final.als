module example

open javametamodel_nofield

run show for exactly 2 Package, 3 Class, 4 Method,  exactly 3 ClassId, exactly 3 MethodId, 4 Body

one sig M extends MethodId{}

fact Pullup {

some c:Class | someSuperClass[c] && someMethod[c] 

someOverloading[1,1] || someOverriding[]
}


pred someSuperClass[c:Class] {
some c2:Class | c2 in c.extend
}

pred someMethod[c:Class] {
 some m:Method | m in c.methods && m.id = M
&& isCaller[m] && someMain[m.id]
}







