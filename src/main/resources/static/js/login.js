// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function IniciarSesion(){
    console.log('entramos a iniciar sesion');
    let datos={};
    datos.email=document.getElementById('txtEmail').value;
    datos.password=document.getElementById('txtPassword').value;

  const request = await fetch('/api/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)//llama toma cualquier objeto y lo convierte en json
  });
  const response = await request.text();

    if(response!='FAIL'){
        localStorage.token=response;
        localStorage.email=datos.email;
        window.location.href='usuarios.html'
    }else{
        alert('Las credenciales son incorrectas, intelo de nuevo')
    }
}

