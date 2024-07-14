getAllEmployees();

    function getAllEmployees(){
        $.ajax({
            url:host + '/api/employees',
            type:'get',
            contentType:'application/json',
            success: function(Data){
                console.log(Data);
                var str = '';
                for(i = 0; i<Data.length; i++){
                    str +=`
                        <tr>
                            <td>${i+1}</td>
                            <td>${Data[i].emp_No}</td>
                            <td>${Data[i].birth_Date}</td>
                            <td>${Data[i].first_Name}</td>
                            <td>${Data[i].last_Name}</td>
                            <td>${Data[i].gender}</td>
                            <td>${Data[i].hire_Date}</td>
                            <td>
                                <button class="btn btn-dark" onClick="form_(${Data[i].id})"> Edit </button>
                                <button class="btn btn-danger" onClick="delete_(${Data[i].id})"> Delete </button>
                            </td>
                        </tr>
                    `
                }
                $('#employeesData').html(str)
            }
        })
    }

    function form_(id){
        var str = '';
            if(id){
                $.ajax({
                    url:host + '/api/employees/' + id,
                    type:'get',
                    contentType:'application/json',
                    async:false,
                    success: function(Data){
                        str = `
                            Nomor Employees : <input class="form-control" type="text" id="emp_no" value="${Data.emp_No}">
                                         <small class="errorField text-danger" type="text" id="errEmpNo"></small> <br>
                            Birth Date : <input class="form-control" type="Date" id="birth_date" value="${Data.birth_Date}">
                                         <small class="errorField text-danger" type="text" id="errBirthDate"></small> <br>
                            First Name : <input class="form-control" type="text" id="first_name" value="${Data.first_Name}">
                                         <small class="errorField text-danger" type="text" id="errFirstName"></small> <br>
                            Last Name : <input class="form-control" type="text" id="last_name" value="${Data.last_Name}">
                                         <small class="errorField text-danger" type="text" id="errLastName"></small> <br>
                            <form id="genderForm">
                                Gender :
                                <p><input type="radio" id="jenisKelaminLaki" name="gender" value="Male" > Male</p>
                                <p><input type="radio" id="jenisKelaminPerempuan" name="gender" value="Female"> Female</p>
                                <small class="errorField text-danger" type="text" id="errGender"></small> <br>
                            </form>
                            Hire Date : <input class="form-control" type="Date" id="hire_date" value="${Data.hire_Date}">
                                        <small class="errorField text-danger" type="text" id="errHireDate"></small> <br>
                            <hr>
                            <button class="btn btn-dark" onClick="editemployees(${Data.id})"> Save </button>
                        `;
                    }
                })
            }
            else {
                str = `
                    Nomor Employees : <input class="form-control" type="text" id="emp_no">
                                      <small class="errorField text-danger" type="text" id="errEmpNo"></small> <br>
                    Birth Date : <input class="form-control" type="Date" id="birth_date">
                                 <small class="errorField text-danger" type="text" id="errBirthDate"></small> <br>
                    First Name : <input class="form-control" type="text" id="first_name">
                                 <small class="errorField text-danger" type="text" id="errFirstName"></small> <br>
                    Last Name : <input class="form-control" type="text" id="last_name">
                                 <small class="errorField text-danger" type="text" id="errLastName"></small> <br>
                    <form id="genderForm">
                        Gender :
                        <p><input type="radio" id="jenisKelaminLaki" name="gender" value="Male" > Male</p>
                        <p><input type="radio" id="jenisKelaminPerempuan" name="gender" value="Female"> Female</p>
                        <small class="errorField text-danger" type="text" id="errGender"></small> <br>
                    </form>
                    Hire Date : <input class="form-control" type="Date" id="hire_date">
                                <small class="errorField text-danger" type="text" id="errHireDate"></small> <br>
                    <hr>
                    <button class="btn btn-dark" onClick="simpanemployees()"> Save </button>
                `;
            }
        $('#mymodal').modal('show')
        $('.modal-title').html('Employees')
        $('.modal-body').html(str);
    }

    function simpanemployees(){
        var emp_no = $("#emp_no").val().trim();
        var birth_date = $("#birth_date").val().trim();
        var first_name = $("#first_name").val().trim();
        var last_name = $("#last_name").val().trim();
        var hire_date = $("#hire_date").val().trim();
        var form = document.getElementById('genderForm');
        var selectedGender = form.querySelector('input[name="gender"]:checked');
        console.log(selectedGender);
        if (selectedGender) {
            var hasilJenisKelamin = selectedGender.value;
            console.log('Jenis Kelamin yang dipilih:', hasilJenisKelamin);
        }
        var verif = false;

        if(emp_no == ""){
            $("#errEmpNo").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(birth_date == ""){
            $("#errBirthDate").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(first_name == ""){
            $("#errFirstName").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(last_name == ""){
            $("#errLastName").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(hasilJenisKelamin == null){
            $("#errJenisKelamin").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(hire_date == ""){
            $("#errHireDate").text("Tidak Boleh Kosong")
            verif = true;
        }
        if (verif){
            return
        }
        const employees = {
            emp_No: emp_no,
            birth_Date: birth_date,
            first_Name: first_name,
            last_Name: last_name,
            gender: hasilJenisKelamin,
            hire_Date: hire_date
        };
        console.log(employees)

        $.ajax({
            url : host + '/api/employees',
            type : 'POST',
            datatype : 'json',
            contentType : "application/json",
            data : JSON.stringify(employees),
            success: function(result){
                console.log(result)
                if(result.status == "ada" ){
                    alert("data sudah ada")
                } else {
                        $('#mymodal').modal('hide')
                        getAllEmployees();
                }
            }
        })
    }

    function editemployees(id){
        var emp_no = $("#emp_no").val().trim();
        var birth_date = $("#birth_date").val().trim();
        var first_name = $("#first_name").val().trim();
        var last_name = $("#last_name").val().trim();
        var hire_date = $("#hire_date").val().trim();
        var form = document.getElementById('genderForm');
        var selectedGender = form.querySelector('input[name="gender"]:checked');
        console.log(selectedGender);
        if (selectedGender) {
            var hasilJenisKelamin = selectedGender.value;
            console.log('Jenis Kelamin yang dipilih:', hasilJenisKelamin);
        } else {
            console.log('Anda Belum Memilih Jenis Kelamin')
        }

        var verif = false;

        if(emp_no == ""){
            $("#errEmpNo").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(birth_date == ""){
            $("#errBirthDate").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(first_name == ""){
            $("#errFirstName").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(last_name == ""){
            $("#errLastName").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(hasilJenisKelamin == null){
            $("#errJenisKelamin").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(hire_date == ""){
            $("#errHireDate").text("Tidak Boleh Kosong")
            verif = true;
        }
        if (verif){
            return
        }
        const employees = {
            emp_No: emp_no,
            birth_Date: birth_date,
            first_Name: first_name,
            last_Name: last_name,
            gender: hasilJenisKelamin,
            hire_Date: hire_date
        };
        console.log(employees)

        $.ajax({
            url : host + '/api/employees/' + id,
            type : 'PUT',
            datatype : 'json',
            contentType : "application/json",
            data : JSON.stringify(employees),
            success: function(result){
                console.log(result)
                if(result.status == "ada" ){
                    alert("data sudah ada")
                } else {
                    $('#mymodal').modal('hide')
                    getAllEmployees();
                }
            }
        })
    }

    function delete_(id){
        var conf = window.confirm("Yakin Menghapus Data Ini ?");
        if(conf){
            $.ajax({
                url : host + '/api/employees/' + id,
                type : 'DELETE',
                contentType : "application/json",
                success: function(result){
                    console.log(result)
                    $('#mymodal').modal('hide')
                    getAllEmployees();
                }
            })
        }
    }