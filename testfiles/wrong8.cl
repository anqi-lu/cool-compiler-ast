(* wrong let *) 
class Main {
    i : Int;

    init(j : Str) : SELF_TYPE {
        (let x : Int <- y + 1 in {
            x + 2;
        })
    };
};