package com.szareckii.searchinthebasefssp.utils

val regionMap: Map<String, String> = mapOf(
        "1 Республика Адыгея" to "1",
        "2 Республика Башкортостан" to "2",
        "3 Республика Бурятия" to "3",
        "4 Республика Алтай" to "4",
        "5 Республика Дагестан" to "5",
        "6 Республика Ингушетия" to "6",
        "7 Кабардино-Балкарская Республика" to "7",
        "8 Республика Калмыкия" to "8",
        "9 Карачаево-Черкесская Республика" to "9",
        "10 Республика Карелия" to "10",
        "11 Республика Коми" to "11",
        "12 Республика Марий Эл" to "12",
        "13 Республика Мордовия" to "13",
        "14 Республика Саха (Якутия)" to "14",
        "15 Республика Северная Осетия - Алания" to "15",
        "16 Республика Татарстан" to "16",
        "17 Республика Тыва" to "17",
        "18 Удмуртская Республика" to "18",
        "19 Республика Хакасия" to "19",
        "20 Чеченская Республика" to "20",
        "21 Чувашская Республика - Чувашия" to "21",
        "22 Алтайский край" to "22",
        "23 Краснодарский край" to "23",
        "24 Красноярский край" to "24",
        "25 Приморский край" to "25",
        "26 Ставропольский край" to "26",
        "27 Хабаровский край" to "27",
        "28 Амурская область" to "28",
        "29 Архангельская область" to "29",
        "30 Астраханская область" to "30",
        "31 Белгородская область" to "31",
        "32 Брянская область" to "32",
        "33 Владимирская область" to "33",
        "34 Волгоградская область" to "34",
        "35 Вологодская область" to "35",
        "36 Воронежская область" to "36",
        "37 Ивановская область" to "37",
        "38 Иркутская область" to "38",
        "39 Калининградская область" to "39",
        "40 Калужская область" to "40",
        "41 Камчатский край" to "41",
        "42 Кемеровская область" to "42",
        "43 Кировская область" to "43",
        "44 Костромская область" to "44",
        "45 Курганская область" to "45",
        "46 Курская область" to "46",
        "47 Ленинградская область" to "47",
        "48 Липецкая область" to "48",
        "49 Магаданская область" to "49",
        "50 Московская область" to "50",
        "51 Мурманская область" to "51",
        "52 Нижегородская область" to "52",
        "53 Новгородская область" to "53",
        "54 Новосибирская область" to "54",
        "55 Омская область" to "55",
        "56 Оренбургская область" to "56",
        "57 Орловская область" to "57",
        "58 Пензенская область" to "58",
        "59 Пермский край" to "59",
        "60 Псковская область" to "60",
        "61 Ростовская область" to "61",
        "62 Рязанская область" to "62",
        "63 Самарская область" to "63",
        "64 Саратовская область" to "64",
        "65 Сахалинская область" to "65",
        "66 Свердловская область" to "66",
        "67 Смоленская область" to "67",
        "68 Тамбовская область" to "68",
        "69 Тверская область" to "69",
        "70 Томская область" to "70",
        "71 Тульская область" to "71",
        "72 Тюменская область" to "72",
        "73 Ульяновская область" to "73",
        "74 Челябинская область" to "74",
        "75 Забайкальский край" to "75",
        "76 Ярославская область" to "76",
        "77 г. Москва" to "77",
        "78 С анкт-Петербург" to "78",
        "79 Еврейская автономная область" to "79",
        "83 Ненецкий автономный округ" to "83",
        "86 Ханты-Мансийский автономный округ - Югра" to "86",
        "87 Чукотский автономный округ" to "87",
        "89 Ямало-Ненецкий автономный округ" to "89",
        "91 Республика Крым" to "91",
        "92 Севастополь" to "92",
        "99 Иные территории, включая город и космодром Байконур" to "99"
).withDefault { "10" }

val regionMapNumber: Map<String, String> = mapOf(
        "1" to "1 Республика Адыгея",
        "2" to "2 Республика Башкортостан",
        "3" to "3 Республика Бурятия",
        "4" to "4 Республика Алтай",
        "5" to "5 Республика Дагестан",
        "6" to "6 Республика Ингушетия",
        "7" to "7 Кабардино-Балкарская Республика",
        "8" to "8 Республика Калмыкия",
        "9" to "9 Карачаево-Черкесская Республика",
        "10" to "10 Республика Карелия",
        "11" to "11 Республика Коми",
        "12" to "12 Республика Марий Эл",
        "13" to "13 Республика Мордовия",
        "14" to "14 Республика Саха (Якутия)",
        "15" to "15 Республика Северная Осетия - Алания",
        "16" to "16 Республика Татарстан",
        "17" to "17 Республика Тыва",
        "18" to "18 Удмуртская Республика",
        "19" to "19 Республика Хакасия",
        "20" to "20 Чеченская Республика",
        "21" to "21 Чувашская Республика - Чувашия",
        "22" to "22 Алтайский край",
        "23" to "23 Краснодарский край",
        "24" to "24 Красноярский край",
        "25" to "25 Приморский край",
        "26" to "26 Ставропольский край",
        "27" to "27 Хабаровский край",
        "28" to "28 Амурская область",
        "29" to "29 Архангельская область",
        "30" to "30 Астраханская область",
        "31" to "31 Белгородская область",
        "32" to "32 Брянская область",
        "33" to "33 Владимирская область",
        "34" to "34 Волгоградская область",
        "35" to "35 Вологодская область",
        "36" to "36 Воронежская область",
        "37" to "37 Ивановская область",
        "38" to "38 Иркутская область",
        "39" to "39 Калининградская область",
        "40" to "40 Калужская область",
        "41" to "41 Камчатский край",
        "42" to "42 Кемеровская область",
        "43" to "43 Кировская область",
        "44" to "44 Костромская область",
        "45" to "45 Курганская область",
        "46" to "46 Курская область",
        "47" to "47 Ленинградская область",
        "48" to "48 Липецкая область",
        "49" to "49 Магаданская область",
        "50" to "50 Московская область",
        "51" to "51 Мурманская область",
        "52" to "52 Нижегородская область",
        "53" to "53 Новгородская область",
        "54" to "54 Новосибирская область",
        "55" to "55 Омская область",
        "56" to "56 Оренбургская область",
        "57" to "57 Орловская область",
        "58" to "58 Пензенская область",
        "59" to "59 Пермский край",
        "60" to "60 Псковская область",
        "61" to "61 Ростовская область",
        "62" to "62 Рязанская область",
        "63" to "63 Самарская область",
        "64" to "64 Саратовская область",
        "65" to "65 Сахалинская область",
        "66" to "66 Свердловская область",
        "67" to "67 Смоленская область",
        "68" to "68 Тамбовская область",
        "69" to "69 Тверская область",
        "70" to "70 Томская область",
        "71" to "71 Тульская область",
        "72" to "72 Тюменская область",
        "73" to "73 Ульяновская область",
        "74" to "74 Челябинская область",
        "75" to "75 Забайкальский край",
        "76" to "76 Ярославская область",
        "77" to "77 г. Москва",
        "78" to "78 С анкт-Петербург",
        "79" to "79 Еврейская автономная область",
        "83" to "83 Ненецкий автономный округ",
        "86" to "86 Ханты-Мансийский автономный округ - Югра",
        "87" to "87 Чукотский автономный округ",
        "89" to "89 Ямало-Ненецкий автономный округ",
        "91" to "91 Республика Крым",
        "92" to "92 Севастополь",
        "99" to "99 Иные территории, включая город и космодром Байконур",
).withDefault { "10 Республика Карелия" }

