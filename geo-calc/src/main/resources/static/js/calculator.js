$(document).ready(function(){

    $("input[name='user_goal']").click(function(){
        var value=$(this).val();
        switch(value){
            case 'education_it':
                $('#goal').text('Выбранная цель: получение образования в сфере IT.');

            break;

            case 'work_it':
                $('#goal').text('Выбранная цель: получение работы в сфере IT.');

            break;

            case 'moving_another_region':
                $('#goal').text('Выбранная цель: переезд в регион с высоким индексом цифровизации.');

            break;
        }
        
    });

    $("input[name='user_education']").click(function(){
        var value=$(this).val();
        switch(value){
            case 'school':
            $('#education').text('Уровень образования: школа.');
              $("input[name='user_field_education']").each(function(){
                $(this).closest('div').hide();
              });
              $('#dropdown').hide();
              break;

            case 'bachelor':
                $('#education').text('Уровень образования: бакалавриат.');
              $("input[name='user_field_education']").each(function(){
                  $(this).closest('div').show();
              });
              $('#dropdown').show();
            break;

            case 'specialty':
                $('#education').text('Уровень образования: специалитет.');
              $("input[name='user_field_education']").each(function(){
                  $(this).closest('div').show();
              });
              $('#dropdown').show();
            break;

            case 'magistracy':
                $('#education').text('Уровень образования: магистратура.');
              $("input[name='user_field_education']").each(function(){
                  $(this).closest('div').show();
              });
              $('#dropdown').show();
            break;
        }
    });

    $("input[name='user_busyness']").click(function(){
        var value=$(this).val();
        switch(value){
            case 'study':
              $("input[name='user_field_busyness']").each(function(){
                $(this).closest('div').hide();
              });

              break;
            default:
              $("input[name='user_field_busyness']").each(function(){
                  $(this).closest('div').show();
              });

              break;
        }
    });

    $("input[name='type_rating']").click(function(){
        var value=$(this).val();
        switch(value){
            case 'top_10':
                $('#region').text('Приоритетные регионы: топ-10.');

            break;

            case 'top_20':
                $('#region').text('Приоритетные регионы: топ-20.');

            break;

            case 'top_30':
                $('#region').text('Приоритетные регионы: топ-30.');

            break;

            default:
                $('#region').text('Приоритетные регионы: все регионы.');

            break;
        }
        
    });

  $("#result").click(function(){
    $("#result_info").show();
    $("#result").hide();
  });

  var table = $('#result_table');

  $("input[name='criteria']").click(function(){
    var value=$(this).val();
    switch(value){
        case 'number_budget_seats':
            $('#result_table tr').remove();

            var header_row_number_budget_seats = $('<tr>');
            header_row_number_budget_seats.append($('<th>').text('Рейтинг региона по количеству бюджетных мест на цифровые специальности'));
            header_row_number_budget_seats.append($('<th>').text('Регион'));
            header_row_number_budget_seats.append($('<th>').text('Количество бюджетных мест на цифровые специальности'));
            header_row_number_budget_seats.append($('<th>').text('Количество контрактных мест на цифровые специальности'));
            header_row_number_budget_seats.append($('<th>').text('Минимальная стоимость контракта на обучение. руб.'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень развития образовательной среды'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень жизни в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Среднедушевые денежные доходы населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Потребительские расходы в среднем на душу населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Индексы потребительских цен (декабрь к декабрю предыдущего года: в процентах)'));
            header_row_number_budget_seats.append($('<th>').text('Численность населения с денежными доходами ниже величины прожиточного минимума/ границы бедности (в процентах от общей численности населения субъекта)'));
            table.append(header_row_number_budget_seats);

            $('body').append(table);

        break;

        case 'tuition_fees':
            $('#result_table tr').remove();
            
            var header_row_number_budget_seats = $('<tr>');
            header_row_number_budget_seats.append($('<th>').text('Рейтинг регион по стоимости обучения на цифровые специальности'));
            header_row_number_budget_seats.append($('<th>').text('Регион'));
            header_row_number_budget_seats.append($('<th>').text('Минимальная стоимость контракта на обучение. руб.'));
            header_row_number_budget_seats.append($('<th>').text('Количество бюджетных мест на цифровые специальности'));
            header_row_number_budget_seats.append($('<th>').text('Количество контрактных мест на цифровые специальности'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень развития образовательной среды'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень жизни в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Среднедушевые денежные доходы населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Потребительские расходы в среднем на душу населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Индексы потребительских цен (декабрь к декабрю)'));
            header_row_number_budget_seats.append($('<th>').text('Численность населения с денежными доходами ниже величины прожиточного минимума/ границы бедности (в процентах от общей численности населения субъекта)'));
            table.append(header_row_number_budget_seats);

            $('body').append(table);

        break;

        case 'number_contract_seats':
            $('#result_table tr').remove();
            
            var header_row_number_budget_seats = $('<tr>');
            header_row_number_budget_seats.append($('<th>').text('Рейтинг региона по количеству контрактных мест на цифровые специальности'));
            header_row_number_budget_seats.append($('<th>').text('Регион'));
            header_row_number_budget_seats.append($('<th>').text('Количество контрактных мест на цифровые специальности'));
            header_row_number_budget_seats.append($('<th>').text('Минимальная стоимость контракта на обучение. руб.'));
            header_row_number_budget_seats.append($('<th>').text('Количество бюджетных мест на цифровые специальности'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень развития образовательной среды'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень жизни в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Среднедушевые денежные доходы населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Потребительские расходы в среднем на душу населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Индексы потребительских цен (декабрь к декабрю)'));
            header_row_number_budget_seats.append($('<th>').text('Численность населения с денежными доходами ниже величины прожиточного минимума/ границы бедности (в процентах от общей численности населения субъекта)'));
            table.append(header_row_number_budget_seats);

            $('body').append(table);

        break;

        case 'estimated_salary':
            $('#result_table tr').remove();
            
            var header_row_number_budget_seats = $('<tr>');
            header_row_number_budget_seats.append($('<th>').text('Рейтинг региона по предполагаемой заработной плате на цифровые профессии'));
            header_row_number_budget_seats.append($('<th>').text('Регион'));
            header_row_number_budget_seats.append($('<th>').text('Среднее значение предполагаемой заработной платы в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Среднее значение предполагаемой заработной платы в столицу региона'));
            header_row_number_budget_seats.append($('<th>').text('Количество вакансий в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Темп прироста количества вакансий в регионе предыдущему году. %'));
            header_row_number_budget_seats.append($('<th>').text('Количество вакансий в столице региона'));
            header_row_number_budget_seats.append($('<th>').text('Темп прироста вакансий в столице региона к предыдущему году. %'));
            header_row_number_budget_seats.append($('<th>').text('Состояние рынка труда в регионе по цифровым проффесиям'));
            header_row_number_budget_seats.append($('<th>').text('Состояние рынка труда в столице региона по цифровым проффесиям'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень жизни в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Среднедушевые денежные доходы населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Потребительские расходы в среднем на душу населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Индексы потребительских цен (декабрь к декабрю предыдущего годы: в процентах)'));
            header_row_number_budget_seats.append($('<th>').text('Численность населения с денежными доходами ниже величины прожиточного минимума/ границы бедности (в процентах от общей численности населения субъекта)'));
            table.append(header_row_number_budget_seats);

            $('body').append(table);

        break;

        case 'number_vacancies':
            $('#result_table tr').remove();
            
            var header_row_number_budget_seats = $('<tr>');
            header_row_number_budget_seats.append($('<th>').text('Рейтинг региона по количеству вакансий на цифровые профессии'));
            header_row_number_budget_seats.append($('<th>').text('Регион'));
            header_row_number_budget_seats.append($('<th>').text('Количество вакансий в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Темп прироста количества вакансий в регион к предыдущему году. %'));
            header_row_number_budget_seats.append($('<th>').text('Количество вакансий в столице региона'));
            header_row_number_budget_seats.append($('<th>').text('Темп прироста количества вакнасий в столице региона к предыдущему. % '));
            header_row_number_budget_seats.append($('<th>').text('Среднее значение предполагаемой заработной платы в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Среднее значение предполагаемой заработной платы в столице региона'));
            header_row_number_budget_seats.append($('<th>').text('Состояние рынка труда в регионе по цифровым проффесиям'));
            header_row_number_budget_seats.append($('<th>').text('Состояние рынка труда в столице региона по цифровым проффесиям'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень жизни в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Среднедушевые денежные доходы населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Потребительские расходы в среднем на душу населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Индексы потребительских цен (декабрь к декабрю предыдущего годы: в процентах)'));
            header_row_number_budget_seats.append($('<th>').text('Численность населения с денежными доходами ниже величины прожиточного минимума/ границы бедности (в процентах от общей численности населения субъекта)'));
            table.append(header_row_number_budget_seats);

            $('body').append(table);

        break;

        case 'moving_to_region':
            $('#result_table tr').remove();
            
            var header_row_number_budget_seats = $('<tr>');
            header_row_number_budget_seats.append($('<th>').text('Рейтинг региона по индексу цифровизации'));
            header_row_number_budget_seats.append($('<th>').text('Регион'));
            header_row_number_budget_seats.append($('<th>').text('Значение индекса цифровизации региона'));
            header_row_number_budget_seats.append($('<th>').text('Значение индекса цифровизации населения региона'));
            header_row_number_budget_seats.append($('<th>').text('Значение индекса цифровизации бизнеса региона'));
            header_row_number_budget_seats.append($('<th>').text('Индекс цифровых профессий'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень развития образовательной среды'));
            header_row_number_budget_seats.append($('<th>').text('Общий уровень жизни в регионе'));
            header_row_number_budget_seats.append($('<th>').text('Среднедушевые денежные доходы населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Потребительские расходы в среднем на душу населения (в месяц: рублей)'));
            header_row_number_budget_seats.append($('<th>').text('Индексы потребительских цен (декабрь к декабрю предыдущего годы: в процентах)'));
            header_row_number_budget_seats.append($('<th>').text('Численность населения с денежными доходами ниже величины прожиточного минимума/ границы бедности (в процентах от общей численности населения субъекта)'));
            table.append(header_row_number_budget_seats);

            $('body').append(table);

        break;
    }
    
});




// Creating table header row


});
