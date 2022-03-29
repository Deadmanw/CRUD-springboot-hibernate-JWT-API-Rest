// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function RegistrarUsuario(){
    let datos={};
    datos.nombre=document.getElementById('txtNombre').value;
    datos.apellido=document.getElementById('txtApellido').value;
    datos.email=document.getElementById('txtEmail').value;
    datos.telefono=document.getElementById('txtTelefono').value;
    datos.password=document.getElementById('txtPassword').value;
    let repetirPassword=document.getElementById('txtRepPassword').value;
    if(repetirPassword != datos.password){
        alert('Las constraseñas no coinciden');
        return;
    }
  const request = await fetch('/api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)//llama toma cualquier objeto y lo convierte en json
  });
  alert('Se a creado la cuenta con exito');
  window.location.href='login.html'
}

