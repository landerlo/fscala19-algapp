

summon [ ((String | String) =:= String) ]

summon [ ((String | Nothing) =:= String) ]

summon [ ((String | Boolean) =:= (Boolean | String)) ]

summon [ ((List[Boolean] & List[Boolean]) =:= List[Boolean]) ]

// This should hold but compiler rejects it
summon [ ((String & Any) =:= Any) ]