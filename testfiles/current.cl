(* wrong6 - let expression *) 
class Main {
    i : Int;

    init(j : Str) : SELF_TYPE {
        (let x : Int <- 1, y : Int <- x in {
            x + 2;
        })
    };
};