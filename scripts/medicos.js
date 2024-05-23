let cadastros = JSON.parse(localStorage.getItem("cadastros")) || [];

function cadastrar(mensagem) {
  let oCrm = document.getElementById("crm");
  let oNome = document.getElementById("nome");
  let oData = document.getElementById("data");
  let oEmail = document.getElementById("email");
  let oTel = document.getElementById("telefone");
  let oSexo = document.getElementById("sexo");
  let oEspecialidade = document.getElementById("especialidade");
  let oUniversidade = document.getElementById("universidade");

  let crm = oCrm.value;
  let nome = oNome.value;
  let data = oData.value;
  let email = oEmail.value;
  let telefone = oTel.value;
  let sexo = oSexo.value;
  let especialidade = oEspecialidade.value;
  let universidade = oUniversidade.value;

  if (
    !crm ||
    !nome ||
    !data ||
    !email ||
    !telefone ||
    !sexo ||
    !especialidade ||
    !universidade
  ) {
    alert("Todos os campos são obrigatórios.");
    return;
  }

  const crmPattern = /^\d{7}\/[A-Z]{2}$/;
  if (!crmPattern.test(crm)) {
    alert("O CRM deve estar no formato 0000000/UF.");
    return;
  }

  const phonePattern = /^[1-9]{2}[0-9]{6}$/;
  if (!phonePattern.test(telefone)) {
    alert("Telefone inválido. Deve estar no formato XXXXXXXX");
    return;
  }

  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(email)) {
    alert("Email inválido.");
    return;
  }

  // Verificar se o CRM já está cadastrado, excluindo temporariamente o médico atual
  let cadastrosTemp = cadastros.filter((medico) => medico.crm !== crm);
  if (cadastrosTemp.some((medico) => medico.crm === crm)) {
    alert("O CRM já está cadastrado.");
    return;
  }

  let oCadastro = {
    crm: crm,
    nome: nome,
    data: data,
    email: email,
    telefone: telefone,
    sexo: sexo,
    especialidade: especialidade,
    universidade: universidade,
  };

  cadastros.unshift(oCadastro);
  localStorage.setItem("cadastros", JSON.stringify(cadastros));

  oCrm.value = "";
  oNome.value = "";
  oData.value = "";
  oEmail.value = "";
  oTel.value = "";
  oSexo.value = "";
  oEspecialidade.value = "";
  oUniversidade.value = "";

  alert(mensagem ?? "Cadastrado efetuado com sucesso");
}

document.addEventListener("DOMContentLoaded", (event) => {
  const medicoParaEditar = JSON.parse(localStorage.getItem("medicoParaEditar"));
  if (medicoParaEditar) {
    document.getElementById("crm").value = medicoParaEditar.crm;
    document.getElementById("nome").value = medicoParaEditar.nome;
    document.getElementById("data").value = medicoParaEditar.data;
    document.getElementById("email").value = medicoParaEditar.email;
    document.getElementById("telefone").value = medicoParaEditar.telefone;
    document.getElementById("sexo").value = medicoParaEditar.sexo;
    document.getElementById("especialidade").value =
      medicoParaEditar.especialidade;
    document.getElementById("universidade").value =
      medicoParaEditar.universidade;

    document.getElementById("cadastrar").style.display = "none";
    document.getElementById("AlterarMedico").style.display = "inline-block";
  }
});

function AlterarMedico() {
  const crm = document.getElementById("crm").value;
  let medicoAtual = cadastros.find((medico) => medico.crm === crm);

  // Remover temporariamente o médico atual da lista
  cadastros = cadastros.filter((medico) => medico.crm !== crm);

  // Chamamos a função cadastrar para verificar se o CRM já existe na lista,
  // porém sem verificar o CRM do médico que estamos editando
  cadastrar("Cadastro atualizado com sucesso");

  // Restaurar o médico atual na lista
  if (medicoAtual) {
    cadastros.unshift(medicoAtual);
  }

  localStorage.removeItem("medicoParaEditar");
  document.getElementById("cadastrar").style.display = "inline-block";
  document.getElementById("AlterarMedico").style.display = "none";
}
