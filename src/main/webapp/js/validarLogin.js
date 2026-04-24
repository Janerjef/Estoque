async function validarLogin(){
    try{
        const res = await fetch("http://localhost:8080/api/perfil");
        const dado = await res.json();

        console.log("PERFIL FRONT: ", dado.perfil);

        if(!dado.perfil || dado.perfil.toLowserCase() !== "admin"){
            
        }
    }catch(e){

    }
}