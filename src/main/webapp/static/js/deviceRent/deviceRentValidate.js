$("#submitForm").validate({
    //显示错误元素
    errorElement:'span',
    errorClass:'text-error',
    //规则
    rules:{
        deviceName:{
            required:true
        },
        rebacktime:{
            required:true
        },
        number:{
            required:true
        },
        days:{
            required:true
        },
        company:{
            required:true
        },
        representative:{
            required:true
        },
        telephone:{
            required:true
        },
        corportion:{
            required:true
        },
        phone:{
            required:true
        },
        identityCard:{
            required:true
        }


    },

    //触发后显示规则
    messages:{
        deviceName:{
            required:"该项不能为空"
        },
        rebacktime:{
            required:"该项不能为空"
        },
        number:{
            required:"该项不能为空"
        },
        days:{
            required:"该项不能为空"
        },
        company:{
            required:"该项不能为空"
        },
        representative:{
            required:"该项不能为空"
        },
        telephone:{
            required:"该项不能为空"
        },
        corportion:{
            required:"该项不能为空"
        },
        phone:{
            required:"该项不能为空"
        },
        identityCard:{
            required:"该项不能为空"
        }

    }




});
