$(function() {

  $("#infoForm input,#infoForm textarea").jqBootstrapValidation({
    preventSubmit: true,
    submitError: function($form, event, errors) {
      // additional error messages or events
    },
    submitSuccess: function($form, event) {
      event.preventDefault(); // prevent default submit behaviour
      // get values from FORM
      var info_name = $("input#info_name").val();
      var info_birth = $("input#info_birth").val();
      var info_job = $("input#info_job").val();
      var info_phone = $("input#info_phone").val();
      var info_time = $("input#info_time").val();
      var info_select = $("textarea#info_select").val();
      var info_privacy = $("textarea#info_privacy").val();
      var firstName = name; // For Success/Failure Message
      // Check for white space in name for Success/Fail message
      if (firstName.indexOf(' ') >= 0) {
        firstName = name.split(' ').slice(0, -1).join(' ');
      }
      $this = $("#sendinfobutton");
      $this.prop("disabled", true); // Disable submit button until AJAX call is complete to prevent duplicate messages
      $.ajax({
        url: "././mail/info.php",
        type: "POST",
        data: {
          info_name: info_name,
          info_birth: info_birth,
          info_job: info_job,
          info_phone: info_phone,
          info_time: info_time,
          info_select: info_select,
        },
        cache: false,
        success: function() {
          // Success message
          $('#info_success').html("<div class='alert alert-success'>");
          $('#info_success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            .append("</button>");
          $('#info_success > .alert-success')
            .append("<strong>담당자 배정 후 연락드리겠습니다.</strong>");
          $('#info_success > .alert-success')
            .append('</div>');
          //clear all fields
          $('#infoForm').trigger("reset");
        },
        error: function() {
          // Fail message
          $('#info_success').html("<div class='alert alert-danger'>");
          $('#info_success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            .append("</button>");
          $('#info_success > .alert-danger').append($("<strong>").text("지금은 신청이 불가합니다. 잠시후 시도해주세요!"));
          $('#info_success > .alert-danger').append('</div>');
          //clear all fields
          $('#infoForm').trigger("reset");
        },
        complete: function() {
          setTimeout(function() {
            $this.prop("disabled", false); // Re-enable submit button when AJAX call is complete
          }, 1000);
        }
      });
    },
    filter: function() {
      return $(this).is(":visible");
    },
  });

  $("a[data-toggle=\"tab\"]").click(function(e) {
    e.preventDefault();
    $(this).tab("show");
  });
});

/*When clicking on Full hide fail/success boxes */
$('#info_name').focus(function() {
  $('#info_success').html('');
});
