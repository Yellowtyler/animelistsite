

            function show(s) {
                  var x = document.getElementById(s);
                  if (x.style.display === "none") {
                    x.style.display = "block";
                  }

                }

            function hide(s) {
                  var x = document.getElementById(s);
                  if (x.style.display === "block") {
                    x.style.display = "none";
                  }

                }

           function validateEmail(email) {
                var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(String(email).toLowerCase());
              }


           function checkForm(form)
              {
                if(form.username.value == "") {
                  show("UsernameError");
                  document.getElementById("UsernameError").innerHTML = "Error: Username cannot be blank!";
                  form.username.focus();
                  return false;
                }
                else
                {
                hide("UsernameError");

                }

                re = /^\w+$/;
                if(!re.test(form.username.value)) {
                  show("UsernameError");
                  document.getElementById("UsernameError").innerHTML =
                  "Error: Username must contain only letters, numbers and underscores!";
                  form.username.focus();
                  return false;
                }
                else
                {

                  hide("UsernameError");

                }

                if(!validateEmail(form.email.value))
                {
                  show("EmailError");
                  document.getElementById("EmailError").innerHTML =
                  "Error: Email is not valid!";
                  form.email.focus();
                  return false;

                }

                else
                {
                  hide("EmailError");
                }

                if(form.password.value != "" && form.password.value == form.confirm_password.value) {

                  if(form.password.value.length < 10) {
                    show("PasswordError");
                    document.getElementById("PasswordError").innerHTML =
                    "Error: Password must contain at least 10 characters!";
                    form.password.focus();
                    hide("NotMatchError");
                    return false;
                  }
                  else
                 {

                  hide("PasswordError");

                }
                  if(form.password.value == form.username.value) {
                    show("PasswordError");
                    document.getElementById("PasswordError").innerHTML =
                    "Error: Password must be different from Username!";
                    hide("NotMatchError");
                    form.password.focus();
                    return false;
                  }
                    else
                 {

                  hide("PasswordError");

                }

                  re = /[0-9]/;
                  if(!re.test(form.password.value)) {
                    show("PasswordError");
                    document.getElementById("PasswordError").innerHTML =
                    "Error: password must contain at least one number (0-9)!";
                    hide("NotMatchError");
                    form.password.focus();
                    return false;
                  }
                    else
                 {

                  hide("PasswordError");

                }
                  re = /[a-z]/;
                  if(!re.test(form.password.value)) {
                    show("PasswordError");
                    document.getElementById("PasswordError").innerHTML =
                    "Error: password must contain at least one lowercase letter (a-z)!";
                    hide("NotMatchError");
                    form.password.focus();
                    return false;
                  }
                    else
                 {

                  hide("PasswordError");

                }
                  re = /[A-Z]/;
                  if(!re.test(form.password.value)) {

                    show("PasswordError");
                    document.getElementById("PasswordError").innerHTML =
                    "Error: password must contain at least one uppercase letter (A-Z)!";
                    hide("NotMatchError");
                    form.password.focus();
                    return false;
                  }
                    else
                 {

                  hide("PasswordError");

                  }

                }

                else {
                  show("NotMatchError");
                  document.getElementById("NotMatchError").innerHTML =
                 "Error: Please check that you've entered and confirmed your password!";
                  form.password.focus();
                  return false;
                }

                alert("You've been successfully registered! To activate your account check your email and confirm your registration.");
                return true;
              }