(* wrong let expression *) 
class Main {
    i : Int;

    init(j : Str) : SELF_TYPE {
        (let x : Int <- k in {
            x + 2;
        })
    };
};