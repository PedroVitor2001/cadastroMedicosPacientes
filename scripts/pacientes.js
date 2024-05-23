let pacientes = JSON.parse(localStorage.getItem("pacientes")) || [];

function cadastrar(mensagem) {
  let oCpf = document.getElementById("cpf");
  let oNome = document.getElementById("nome");
  let oData = document.getElementById("data");
  let oEmail = document.getElementById("email");
  let oTel = document.getElementById("telefone");
  let oSexo = document.getElementById("sexo");
  let oPlano = document.getElementById("plano");

  let cpf = oCpf.value;
  let nome = oNome.value;
  let data = oData.value;
  let email = oEmail.value;
  let telefone = oTel.value;
  let sexo = oSexo.value;
  let plano = oPlano.value;
  let oCadastro = {};

  if (
    !cpf ||
    !nome ||
    !data ||
    !email ||
    !telefone ||
    !sexo ||
    !plano
  ) {
    alert("Todos os campos são obrigatórios.");
    return;
  }

  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(email)) {
    alert("Email inválido.");
    return;
  }

  pacientes = pacientes.filter((paciente) => paciente.cpf !== cpf);

  oCadastro.cpf = cpf;
  oCadastro.nome = nome;
  oCadastro.data = data;
  oCadastro.email = email;
  oCadastro.telefone = telefone;
  oCadastro.sexo = sexo;
  oCadastro.plano = plano;

  let flag = false;
  for (i = 0; i < pacientes.length; i++) {
    if (pacientes[i].cpf == cpf) {
      flag = true;
    }
  }
  if (flag) {
    alert(
      "O cadastro deste objeto não foi possível, pois as informações já foram cadastradas anteriormente."
    );
  } else {
    pacientes.unshift(oCadastro);
    localStorage.setItem("pacientes", JSON.stringify(pacientes));
    oCpf.value = "";
    oNome.value = "";
    oData.value = "";
    oEmail.value = "";
    oTel.value = "";
    oSexo.value = "";
    oPlano.value = "";
    alert(mensagem ?? "Cadastrado efetuado com sucesso");
  }
}

document.addEventListener("DOMContentLoaded", (event) => {
  const pacienteParaEditar = JSON.parse(localStorage.getItem("pacienteParaEditar"));
  if (pacienteParaEditar) {
    document.getElementById("cpf").value = pacienteParaEditar.cpf;
    document.getElementById("nome").value = pacienteParaEditar.nome;
    document.getElementById("data").value = pacienteParaEditar.data;
    document.getElementById("email").value = pacienteParaEditar.email;
    document.getElementById("telefone").value = pacienteParaEditar.telefone;
    document.getElementById("sexo").value = pacienteParaEditar.sexo;
    document.getElementById("plano").value =
    pacienteParaEditar.plano;

    document.getElementById("cadastrar").style.display = "none";
    document.getElementById("AlterarPaciente").style.display = "inline-block";
  }
});


function AlterarPaciente() {
  const cpf = document.getElementById("cpf").value;
  pacientes = pacientes.filter((paciente) => paciente.cpf !== cpf);
  cadastrar("Cadastro atualizado com sucesso");

  localStorage.removeItem("pacienteParaEditar");
  document.getElementById("cadastrar").style.display = "inline-block";
  document.getElementById("Alterarpaciente").style.display = "none";
}
