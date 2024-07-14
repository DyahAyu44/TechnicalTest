getAllDepartments();

    function getAllDepartments() {
        $.ajax({
            url:host + '/api/departments',
            type:'get',
            contentType:'application/json',
            success: function(Data){
                console.log(Data);
                var str = '';
                for(i = 0; i<Data.length; i++){
                    str +=`
                        <tr>
                            <td>${i+1}</td>
                            <td>${Data[i].dept_No}</td>
                            <td>${Data[i].dept_Name}</td>
                            <td>
                                <button class="btn btn-dark" onClick="form_(${Data[i].id})"> Edit </button>
                                <button class="btn btn-danger" onClick="delete_(${Data[i].id})"> Delete </button>
                            </td>
                        </tr>
                    `
                }
                $('#departmentsData').html(str)
            }
        })
    }

    function form_(id){
        var str = '';
            if(id){
                $.ajax({
                    url:host + '/api/departments/' + id,
                    type:'get',
                    contentType:'application/json',
                    async:false,
                    success: function(Data){
                        str = `
                            Nomor Department : <input class="form-control" type="text" id="dept_no" value="${Data.dept_No}">
                                                <small class="errorField text-danger" type="text" id="errDeptNo"></small> <br>
                            Department Name : <input class="form-control" type="text" id="dept_name" value="${Data.dept_Name}">
                                                <small class="errorField text-danger" type="text" id="errDeptName"></small> <br>
                            <hr>
                            <button class="btn btn-dark" onClick="editdepartments(${Data.id})"> Save </button>
                        `;
                    }
                })
            }
            else {
                str = `
                    Nomor Department : <input class="form-control" type="text" id="dept_no">
                                      <small class="errorField text-danger" type="text" id="errDeptNo"></small> <br>
                    Department Name : <input class="form-control" type="text" id="dept_name">
                                        <small class="errorField text-danger" type="text" id="errDeptName"></small> <br>
                    <hr>
                    <button class="btn btn-dark" onClick="simpandepartments()"> Save </button>
                `;
            }
        $('#mymodal').modal('show')
        $('.modal-title').html('Departments')
        $('.modal-body').html(str);
    }

    function simpandepartments(){
        var dept_no = $("#dept_no").val().trim();
        var dept_name = $("#dept_name").val().trim();

        var verif = false;

        if(dept_no == ""){
            $("#errDeptNo").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(dept_name == ""){
            $("#errDeptName").text("Tidak Boleh Kosong")
            verif = true;
        }
        if (verif){
            return
        }
        const departments = {
            dept_No: dept_no,
            dept_Name: dept_name
        };
        console.log(departments)

        $.ajax({
            url : host + '/api/departments',
            type : 'POST',
            datatype : 'json',
            contentType : "application/json",
            data : JSON.stringify(departments),
            success: function(result){
                console.log(result)
                if(result.status == "ada" ){
                    alert("data sudah ada")
                } else {
                    $('#mymodal').modal('hide')
                    getAllDepartments();
                }
            }
        })
    }

    function editdepartments(id){
        var dept_no = $("#dept_no").val().trim();
        var dept_name = $("#dept_name").val().trim();

        var verif = false;

        if(dept_no == ""){
            $("#errDeptNo").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(dept_name == ""){
            $("#errDeptName").text("Tidak Boleh Kosong")
            verif = true;
        }
        if (verif){
            return
        }
        const departments = {
            dept_No: dept_no,
            dept_Name: dept_name
        };
        console.log(departments)

        $.ajax({
            url : host + '/api/departments/' + id,
            type : 'PUT',
            datatype : 'json',
            contentType : "application/json",
            data : JSON.stringify(departments),
            success: function(result){
                console.log(result)
                if(result.status == "ada" ){
                    alert("data sudah ada")
                } else {
                    $('#mymodal').modal('hide')
                    getAllDepartments();
                }
            }
        })
    }

    function delete_(id){
        var conf = window.confirm("Yakin Menghapus Data Ini ?");
        if(conf){
            $.ajax({
                url : host + '/api/departments/' + id,
                type : 'DELETE',
                contentType : "application/json",
                success: function(result){
                    console.log(result)
                    $('#mymodal').modal('hide')
                    getAllDepartments();
                }
            })
        }
    }