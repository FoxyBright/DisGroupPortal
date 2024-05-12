package com.study.disgroupportal.model.portal

import com.study.disgroupportal.R
import java.util.UUID

enum class Division(
    override val id: String,
    override val image: Int,
    override val title: String,
    override val description: String
) : Tile {
    CONSULTING(
        id = UUID.randomUUID().toString(),
        image = R.drawable.consulting,
        title = "Консалтинг",
        description = "Подразделение консультантов руководителей " +
                "предприятий по широкому кругу вопросов.",
    ),

    MARKETING(
        id = UUID.randomUUID().toString(),
        image = R.drawable.marketing,
        title = "Маркетинг",
        description = "Подразделение компании для управления " +
                "рекламными процессами и организации коммуникаций."
    ),

    ACCOUNTING(
        id = UUID.randomUUID().toString(),
        image = R.drawable.accounting,
        title = "Бухгалтерия",
        description = "Штатно-структурное подразделение предназначенное " +
                "для  аккумулирования данных  о имуществе и  обязательствах."
    ),

    CLIENT_HELP(
        id = UUID.randomUUID().toString(),
        image = R.drawable.client_help,
        title = "Клиентский сервис",
        description = "Подразделение помощи и консультации, " +
                "которые компания предоставляет своим клиентам"
    ),

    BUSINESS_HELP(
        id = UUID.randomUUID().toString(),
        image = R.drawable.buisens_help,
        title = "Бизнес поддержка",
        description = "Подразделение специалистов поддержки, " +
                "которые помогут клиентам разобраться с продуктом."
    )
}
