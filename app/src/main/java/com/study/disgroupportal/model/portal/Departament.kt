package com.study.disgroupportal.model.portal

import com.study.disgroupportal.R
import com.study.disgroupportal.model.portal.Division.ACCOUNTING
import com.study.disgroupportal.model.portal.Division.BUSINESS_HELP
import com.study.disgroupportal.model.portal.Division.CLIENT_HELP
import com.study.disgroupportal.model.portal.Division.CONSULTING
import com.study.disgroupportal.model.portal.Division.MARKETING
import java.util.UUID

enum class Departament(
    override val id: String = UUID.randomUUID().toString(),
    override val image: Int,
    override val title: String,
    override val description: String,
    val division: Division
) : Tile {

    Expert_Management(
        image = R.drawable.expert,
        title = "Экспертное управление",
        description = "Подготовка экспертных заключений, аналитических докладов и иных необходимых материалов.",
        division = CONSULTING
    ),
    Evaluation_Service(
        image = R.drawable.marking,
        title = "Служба оценки",
        description = "Широкопрофильный отдел, который оказывает услуги по экспертизе и оценке любых видов продуктов.",
        division = CONSULTING
    ),
    Research_Office(
        image = R.drawable.research,
        title = "Управление исследований",
        description = "Отдел проектирования и руководства процессами.",
        division = CONSULTING
    ),
    Analytical_Department(
        image = R.drawable.analitycs,
        title = "Аналитический отдел",
        description = "Оценивает финансовое состояние компании, определяет ликвидность и рентабельность фирмы.",
        division = CONSULTING
    ),

    Patent_Marketing(
        image = R.drawable.patent,
        title = "Патентный маркетинг",
        description = "Отдел защиты бизнеса от правовых посягательств.",
        division = MARKETING
    ),
    Patent_Rating(
        image = R.drawable.rating,
        title = "Патентный рейтинг",
        description = "Эксперты в области интеллектуальной собственности, которые представляют интересы клиента.",
        division = MARKETING
    ),
    Advertising_Department(
        image = R.drawable.advertising,
        title = "Отдел рекламы",
        description = "Отдел проведения мероприятий в сфере маркетинговых коммуникаций и реализацией выбранной маркетинговой стратегии.",
        division = MARKETING
    ),
    Sales_Department(
        image = R.drawable.sales,
        title = "Отдел продаж",
        description = "Структурное подразделение компании, которое отвечает за продажу товаров или услуг.",
        division = MARKETING
    ),


    Financial_Expertise(
        image = R.drawable.fin_help,
        title = "Финансовая экспертиза",
        description = "Отдел, заведующий достоверностью финансовой отчетности предприятия.",
        division = ACCOUNTING
    ),
    Investments_Management(
        image = R.drawable.investion,
        title = "Управление инвистиций",
        description = "Профессиональное управление активами различных ценных бумаг, включая пакеты акций, облигации и другие активы.",
        division = ACCOUNTING
    ),
    Investments_Asset(
        image = R.drawable.action,
        title = "Управление активами",
        description = "Отдел разработки, эксплуатации, технического обслуживания, модернизации и распоряжения активами.",
        division = ACCOUNTING
    ),
    Invest_Banking(
        image = R.drawable.banking,
        title = "Инвест банкинг",
        description = "Отдел проводящий операции по выводу ценных бумаг акционерных обществ на финансовый рынок.",
        division = ACCOUNTING
    ),


    Licensing_Management(
        image = R.drawable.license,
        title = "Лицензионный менеджмент",
        description = "Отдел управления лицензиями на программное обеспечение.",
        division = BUSINESS_HELP
    ),
    Risk_Management(
        image = R.drawable.risk,
        title = "Риск менеджмент",
        description = "Отдел идентифицации, анализа, оценки и управления потенциальными угрозами или негативными последствиями.",
        division = BUSINESS_HELP
    ),
    Strategic_Analysis(
        image = R.drawable.analis,
        title = "Стратегический анализ",
        description = "Отдел исследования деятельности компании и факторов окружающей среды, влияющих на её положение и конкурентоспособность.",
        division = BUSINESS_HELP
    ),
    Development_Department(
        image = R.drawable.develop,
        title = "Отдел разработок",
        description = "Занимается разработкой технических проектов ПО, построением архитектуры программных систем, определением технических и программных требований к ПО.",
        division = BUSINESS_HELP
    ),


    Operational_Management(
        image = R.drawable.operating,
        title = "Операционное управление",
        description = "Отдел управления операциями, которые позволяют достигать результата деятельности компании.",
        division = CLIENT_HELP
    ),
    Insurance_Products(
        image = R.drawable.strah,
        title = "Страховые продукты",
        description = "Отдел возмещения убытков страхователю, произошедшие вследствие оговоренных страховых событий.",
        division = CLIENT_HELP
    ),
    Support_Service(
        image = R.drawable.callcenter,
        title = "Служба системного сопровождения",
        description = "Отдельная служба, созданная для получения и обработки обращений клиентов.",
        division = CLIENT_HELP
    ),
    Consulting_Department(
        image = R.drawable.client_help,
        title = "Отдел консультации",
        description = "Отдел услуг экспертной помощи и консультаций по различным вопросам в бизнесе.",
        division = CLIENT_HELP
    );

    companion object {
        fun getListByDivision(division: Division) =
            entries.filter { it.division == division }
    }
}