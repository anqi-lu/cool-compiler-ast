(* wrong let *) 
class Main {
    i : Int;
    j : Int;

    init(j : Str) : SELF_TYPE {
        (let x : Int <- x + 1 in {
            x + 2;
        })
    };
};