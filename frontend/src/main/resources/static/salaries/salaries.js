getAllSalaries();

    function getAllSalaries(){
        var employees = [];
            $.ajax({
                url:host + '/api/employees',
                type:'get',
                contentType:'application/json',
                async:false,
                success: function(Data){
                    employees = Data;
                   console.log(employees);
                }
            })

            $.ajax({
                url:host + '/api/salaries',
                type:'get',
                contentType:'application/json',
                async:false,
                success: function(Data){
                    console.log(Data);
                    console.log(employees);
                    var str = '';
                    for(i = 0; i<Data.length; i++){
                        str +=`
                            <tr>
                                <td>${i+1}</td>
                                <td>${Data[i].employees.emp_No}</td>
                                <td>${Data[i].salary}</td>
                                <td>${Data[i].from_Date}</td>
                                <td>${Data[i].to_Date}</td>
                                <td>
                                    <button class="btn btn-dark" onClick="form_(${Data[i].id})"> Edit </button>
                                    <button class="btn btn-danger" onClick="delete_(${Data[i].id})"> Delete </button>
                                </td>
                            </tr>
                        `
                    }
                    $('#salariesData').html(str)
                }
            })
        }

        function form_(id){
            var str = ``;
            var employees;
                $.ajax({
                    url:host + '/api/employees',
                    type:'get',
                    contentType:'application/json',
                    async:false,
                    success: function(Data){
                        employees = Data;
                    }
                })

                if(id){
                    $.ajax({
                        url:host + '/api/salaries/' + id,
                        type:'get',
                        contentType:'application/json',
                        async:false,
                        success: function(Data){
                             str =`Nomor Employee :
                                    <select   class="form-select" id="employees" >`;
                                console.log(employees);
                                for(i=0; i < employees.length; i++){
                                    if (Data.employees.id === employees[i].id){
                                        str += `<option value="${employees[i].id}" selected>${employees[i].emp_No}</option>`;
                                    } else {
                                        str += `<option value="${employees[i].id}">${employees[i].emp_No}</option>`;
                                    }
                                }
                                str += `</select>
                                Title : <input class="form-control" type="text" id="salary" value="${Data.salary}">
                                        <small class="errorField text-danger" type="text" id="errSalary"></small> <br>
                                From Date : <input class="form-control" type="Date" id="from_date" value="${Data.from_Date}">
                                            <small class="errorField text-danger" type="text" id="errFromDate"></small> <br>
                                To Date : <input class="form-control" type="Date" id="to_date" value="${Data.to_Date}">
                                            <small class="errorField text-danger" type="text" id="errToDate"></small> <br>
                                <hr>
                                <button class="btn btn-primary" onclick="editSalary(${Data.id})">Save</button>
                            `;
                        }
                    })
                }
                else {
                    str = ` Nomor Employee :
                            <select class="form-select" id="employees" >`;
                        console.log(employees);
                        for(let i=0; i < employees.length; i++){
                            str += `<option value="${employees[i].id}" >${employees[i].emp_No}</option>`;
                        }
                        str += `</select>
                        Title : <input class="form-control" type="text" id="salary">
                                <small class="errorField text-danger" type="text" id="errSalary"></small> <br>
                        From Date : <input class="form-control" type="Date" id="from_date">
                                    <small class="errorField text-danger" type="text" id="errFromDate"></small> <br>
                        To Date : <input class="form-control" type="Date" id="to_date">
                                    <small class="errorField text-danger" type="text" id="errToDate"></small> <br>
                        <hr>
                        <button class="btn btn-primary" onclick="simpanTitles()">Save</button>
                    `;
                }
            $('#mymodal').modal('show')
            $('.modal-title').html('Salaries')
            $('.modal-body').html(str);
        }

        function simpanTitles(){
            var employees = $("#employees").val();
            var salary = $("#salary").val().trim();
            var from_date = $("#from_date").val().trim();
            var to_date = $("#to_date").val().trim();

            var verif = false;

            if(salary == ""){
                $("#errSalary").text("Tidak Boleh Kosong")
                verif = true;
            }
            if(from_date == ""){
                $("#errFromDate").text("Tidak Boleh Kosong")
                verif = true;
            }
            if(to_date == ""){
                $("#errToDate").text("Tidak Boleh Kosong")
                verif = true;
            }
            if (verif){
                return
            }

            const salaries = {
                employeesId: employees,
                salary: salary,
                from_Date: from_date,
                to_Date: to_date
            };
            console.log(titles)

            $.ajax({
                url : host + '/api/salaries',
                type : 'POST',
                datatype : 'json',
                contentType : "application/json",
                data : JSON.stringify(titles),
                success: function(result){
                    console.log(result)
                    console.log("Sending Titles Data: ", titles);
                    if(result.status == "ada" ){
                        alert("data sudah ada")
                    } else {
                        $('#mymodal').modal('hide')
                        getAllSalaries();
                    }
                }
            })
        }

    function editSalaries(id){
        var employees = $("#employess").val();
        var salary = $("#salary").val().trim();
        var from_date = $("#from_date").val().trim();
        var to_date = $("#to_date").val().trim();

        var verif = false;

        if(salary == ""){
            $("#errSalary").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(from_date == ""){
            $("#errFromDate").text("Tidak Boleh Kosong")
            verif = true;
        }
        if(to_date == ""){
            $("#errToDate").text("Tidak Boleh Kosong")
            verif = true;
        }
        if (verif){
            return
        }

        const salaries = {
            employees: employees,
            salary: salary,
            from_Date: from_date,
            to_Date: to_date
        };
        console.log(salaries)

        $.ajax({
            url : host + '/api/salaries/' + id,
    	    type : 'PUT',
    	    datatype : 'json',
            contentType : "application/json",
    	    data : JSON.stringify(salaries),
    	    success: function(result){
    	        console.log(result)
    	        if(result.status == "ada" ){
                    alert("data sudah ada")
                } else {
                    $('#mymodal').modal('hide')
                    getAllSalaries();
                }
    	    }
        })
    }

    function delete_(id){
        var conf = window.confirm("Yakin Menghapus Data Ini ?");
            if(conf){
            $.ajax({
                url : host + '/api/salaries/' + id,
                type : 'DELETE',
                contentType : "application/json",
                success: function(result){
                    console.log(result)
                    $('#mymodal').modal('hide')
                    getAllSalaries();
                }
            })
        }
    }