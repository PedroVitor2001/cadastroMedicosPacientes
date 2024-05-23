let pacientes = JSON.parse(localStorage.getItem("pacientes")) || [];

function mostrar() {
  if (pacientes.length === 0) {
    alert("Nenhum objeto encontrado");
    return;
  }

  let tbody = document.querySelector("#pacientesTable tbody");
  tbody.innerHTML = "";

  for (i = 0; i < pacientes.length; i++) {
    let row = tbody.insertRow();
    row.insertCell(0).textContent = pacientes[i].nome;
    row.insertCell(1).textContent = pacientes[i].cpf;
    row.insertCell(2).textContent = pacientes[i].sexo;
    row.insertCell(3).textContent = pacientes[i].data;
    row.insertCell(4).textContent = pacientes[i].email;
    row.insertCell(5).textContent = pacientes[i].telefone;
    row.insertCell(6).textContent = pacientes[i].plano;
  }
}

function ListarUm() {
  let cpf = document.getElementById("icpf").value;
  if (cpf == "") {
    alert("Digite o CPF");
    return;
  }

  let paciente = pacientes.find((paciente) => paciente.cpf == cpf);
  if (!paciente) {
    alert("Cadastro não encontrado.");
  } else {

    let tbody = document.querySelector("#pacientesTable tbody");
    tbody.innerHTML = "";
    let row = tbody.insertRow();
  
    row.insertCell(0).textContent = paciente.nome;
    row.insertCell(1).textContent = paciente.cpf;
    row.insertCell(2).textContent = paciente.sexo;
    row.insertCell(3).textContent = paciente.data;
    row.insertCell(4).textContent = paciente.email;
    row.insertCell(5).textContent = paciente.telefone;
    row.insertCell(6).textContent = paciente.plano;
  }
}

function Excluir() {
  let cpf = document.getElementById("icpf").value;
  if (cpf == "") {
    alert("Digite o CPF");
    return;
  }
  let paciente = pacientes.find((paciente) => paciente.cpf == cpf);
  if (!paciente) {
    alert("Cadastro não encontrado.");
    return;
  }
  pacientes = pacientes.filter((paciente) => paciente.cpf != cpf);
  alert("Objeto removido com sucesso.");
  mostrar();
}

function Alterar() {
  let cpf = document.getElementById("icpf").value;
  if (cpf == "") {
    alert("Digite o CPF");
    return;
  }
  let paciente = pacientes.find((paciente) => paciente.cpf == cpf);
  if (!paciente) {
    alert("Cadastro não encontrado.");
  } else {
    localStorage.setItem("pacienteParaEditar", JSON.stringify(paciente));
    window.location.href = "pacientes.html";
  }
}