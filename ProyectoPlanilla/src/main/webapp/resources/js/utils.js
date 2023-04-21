/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function validarTipoUsuario() {

    var tipoUsuario = document.getElementById('TipoUsuario');

    if (tipoUsuario.innerHTML === "Recursos Humanos") {
        document.getElementById('planillas').style.display = "none";
        document.getElementById('planillaDestajo').style.display = "none";
        document.getElementById('Man1').style.display = "none";
        document.getElementById('Man2').style.display = "none";
        document.getElementById('Man4').style.display = "none";
        document.getElementById('Reportes').style.display = "none";
    } else if (tipoUsuario.innerHTML === "Planillero") {
        document.getElementById('Man3').style.display = "none";
    }

}
