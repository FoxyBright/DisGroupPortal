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
        image = R.drawable.consulting,
        title = "Экспертное управление",
        description = "",
        division = CONSULTING
    ),
    Evaluation_Service(
        image = R.drawable.consulting,
        title = "Служба оценки",
        description = "",
        division = CONSULTING
    ),
    Research_Office(
        image = R.drawable.consulting,
        title = "Управление исследований",
        description = "",
        division = CONSULTING
    ),
    Analytical_Department(
        image = R.drawable.consulting,
        title = "Аналитический отдел",
        description = "",
        division = CONSULTING
    ),


    Patent_Marketing(
        image = R.drawable.marketing,
        title = "Патентный маркетинг",
        description = "",
        division = MARKETING
    ),
    Patent_Rating(
        image = R.drawable.marketing,
        title = "Патентный рейтинг",
        description = "",
        division = MARKETING
    ),
    Advertising_Department(
        image = R.drawable.marketing,
        title = "Отдел рекламы",
        description = "",
        division = MARKETING
    ),
    Sales_Department(
        image = R.drawable.marketing,
        title = "Отдел продаж",
        description = "",
        division = MARKETING
    ),


    Financial_Expertise(
        image = R.drawable.accounting,
        title = "Финансовая экспертиза",
        description = "",
        division = ACCOUNTING
    ),
    Investments_Management(
        image = R.drawable.accounting,
        title = "Управление инвистиций",
        description = "",
        division = ACCOUNTING
    ),
    Investments_Asset(
        image = R.drawable.accounting,
        title = "Управление активами",
        description = "",
        division = ACCOUNTING
    ),
    Invest_Banking(
        image = R.drawable.accounting,
        title = "Инвест банкинг",
        description = "",
        division = ACCOUNTING
    ),


    Licensing_Management(
        image = R.drawable.buisens_help,
        title = "Лицензионный менеджмент",
        description = "",
        division = BUSINESS_HELP
    ),
    Risk_Management(
        image = R.drawable.buisens_help,
        title = "Риск менеджмент",
        description = "",
        division = BUSINESS_HELP
    ),
    Strategic_Analysis(
        image = R.drawable.buisens_help,
        title = "Стратегический анализ",
        description = "",
        division = BUSINESS_HELP
    ),
    Development_Department(
        image = R.drawable.buisens_help,
        title = "Отдел разработок",
        description = "",
        division = BUSINESS_HELP
    ),


    Operational_Management(
        image = R.drawable.client_help,
        title = "Операционное управление",
        description = "",
        division = CLIENT_HELP
    ),
    Insurance_Products(
        image = R.drawable.client_help,
        title = "Страховые продукты",
        description = "",
        division = CLIENT_HELP
    ),
    Support_Service(
        image = R.drawable.client_help,
        title = "Служба системного сопровождения",
        description = "",
        division = CLIENT_HELP
    ),
    Consulting_Department(
        image = R.drawable.client_help,
        title = "Отдел консультации",
        description = "",
        division = CLIENT_HELP
    );

    companion object {
        fun getListByDivision(division: Division) =
            entries.filter { it.division == division }
    }
}