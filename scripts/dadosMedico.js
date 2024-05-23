let cadastros = JSON.parse(localStorage.getItem("cadastros")) || [];

function mostrar() {
  if (cadastros.length === 0) {
    alert("Nenhum objeto encontrado");
    return;
  }

  let tbody = document.querySelector("#medicosTable tbody");
  tbody.innerHTML = "";

  for (let i = 0; i < cadastros.length; i++) {
    let row = tbody.insertRow();
    row.insertCell(0).textContent = cadastros[i].nome;
    row.insertCell(1).textContent = cadastros[i].crm;
    row.insertCell(2).textContent = cadastros[i].sexo;
    row.insertCell(3).textContent = cadastros[i].data;
    row.insertCell(4).textContent = cadastros[i].email;
    row.insertCell(5).textContent = cadastros[i].telefone;
    row.insertCell(6).textContent = cadastros[i].especialidade;
    row.insertCell(7).textContent = cadastros[i].universidade;
  }
}

function ListarUm() {
  let crm = document.getElementById("icrm").value.trim();
  if (crm === "") {
    alert("Digite o CRM");
    return;
  }

  let medico = cadastros.find((medico) => medico.crm === crm);
  if (!medico) {
    alert("Cadastro não encontrado.");
  } else {
    let tbody = document.querySelector("#medicosTable tbody");
    tbody.innerHTML = "";

    let row = tbody.insertRow();
    row.insertCell(0).textContent = medico.nome;
    row.insertCell(1).textContent = medico.crm;
    row.insertCell(2).textContent = medico.sexo;
    row.insertCell(3).textContent = medico.data;
    row.insertCell(4).textContent = medico.email;
    row.insertCell(5).textContent = medico.telefone;
    row.insertCell(6).textContent = medico.especialidade;
    row.insertCell(7).textContent = medico.universidade;
  }
}

function Excluir() {
  let crm = document.getElementById("icrm").value;
  if (crm == "") {
    alert("Digite o CRM");
    return;
  }
  let medico = cadastros.find((medico) => medico.crm == crm);
  if (!medico) {
    alert("Cadastro não encontrado.");
    return;
  }
  cadastros = cadastros.filter((medico) => medico.crm != crm);
  localStorage.setItem("cadastros", JSON.stringify(cadastros));
  alert("Objeto removido com sucesso.");
  mostrar();
}

function Alterar() {
  let crm = document.getElementById("icrm").value;
  if (crm === "") {
    alert("Digite o CRM");
    return;
  }
  let medico = cadastros.find((medico) => medico.crm === crm);
  if (!medico) {
    alert("Cadastro não encontrado.");
  } else {
    localStorage.setItem("medicoParaEditar", JSON.stringify(medico));
    window.location.href = "medicos.html";
  }
}
