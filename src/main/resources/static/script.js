const BASE_URL="http://localhost:8080";

function output(data){
  document.getElementById("output").textContent =
    typeof data==="string" ? data : JSON.stringify(data,null,2);
}

function authHeaders(){
  const u=localStorage.getItem("username");
  const p=localStorage.getItem("password");
  if(!u||!p) throw new Error("Please login first.");
  return {
    "Content-Type":"application/json",
    "Authorization":"Basic "+btoa(u+":"+p)
  };
}

function saveLogin(){
  const u=document.getElementById("username").value.trim();
  const p=document.getElementById("password").value;
  if(!u||!p){document.getElementById("loginStatus").textContent="Enter username and password.";return;}
  localStorage.setItem("username",u);
  localStorage.setItem("password",p);
  document.getElementById("loginStatus").textContent="Login details saved.";
}

function logout(){
  localStorage.removeItem("username");
  localStorage.removeItem("password");
  document.getElementById("loginStatus").textContent="Logged out.";
}

async function request(url,options){
  try{
    const response=await fetch(url,options);
    const text=await response.text();
    let data;
    try{data=text?JSON.parse(text):"Request successful";}catch{data=text;}
    output({status:response.status,data:data});
  }catch(e){
    output({error:e.message,hint:"Make sure Spring Boot is running on localhost:8080. If using Live Server, configure CORS."});
  }
}

async function registerUser(){
  const username=document.getElementById("registerUsername").value.trim();
  const password=document.getElementById("registerPassword").value;
  await request(BASE_URL+"/auth/register",{
    method:"POST",headers:{"Content-Type":"application/json"},
    body:JSON.stringify({username,password})
  });
}

async function addDepartment(){
  try{
    await request(BASE_URL+"/department/add",{
      method:"POST",headers:authHeaders(),
      body:JSON.stringify({name:document.getElementById("departmentName").value.trim()})
    });
  }catch(e){output(e.message);}
}

async function createStudent(){
  try{
    const body={
      name:document.getElementById("studentName").value.trim(),
      age:Number(document.getElementById("studentAge").value),
      email:document.getElementById("studentEmail").value.trim(),
      rollNumber:Number(document.getElementById("studentRoll").value),
      subject:document.getElementById("studentSubject").value.trim(),
      departmentId:Number(document.getElementById("studentDepartmentId").value)
    };
    await request(BASE_URL+"/students/create",{
      method:"POST",headers:authHeaders(),body:JSON.stringify(body)
    });
  }catch(e){output(e.message);}
}

async function getStudent(){
  try{
    const id=document.getElementById("studentId").value;
    await request(BASE_URL+"/students/get/"+id,{method:"GET",headers:authHeaders()});
  }catch(e){output(e.message);}
}

async function getAllStudents(){
  try{await request(BASE_URL+"/students/getAll",{method:"GET",headers:authHeaders()});}
  catch(e){output(e.message);}
}

async function searchStudents(){
  try{
    const name=encodeURIComponent(document.getElementById("searchName").value.trim());
    await request(BASE_URL+"/students/search?name="+name,{method:"GET",headers:authHeaders()});
  }catch(e){output(e.message);}
}

async function updateStudent(){
  try{
    const id=document.getElementById("updateId").value;
    const body={
      name:document.getElementById("updateName").value.trim(),
      age:Number(document.getElementById("updateAge").value),
      email:document.getElementById("updateEmail").value.trim(),
      rollNumber:Number(document.getElementById("updateRoll").value),
      subject:document.getElementById("updateSubject").value.trim(),
      departmentId:Number(document.getElementById("updateDepartmentId").value)
    };
    await request(BASE_URL+"/students/update/"+id,{
      method:"PUT",headers:authHeaders(),body:JSON.stringify(body)
    });
  }catch(e){output(e.message);}
}

async function deleteStudent(){
  try{
    const id=document.getElementById("studentId").value;
    await request(BASE_URL+"/students/delete/"+id,{
      method:"DELETE",headers:authHeaders()
    });
  }catch(e){output(e.message);}
}