package org.example.notifier

import org.webscene.client.html.bootstrap.Column
import org.webscene.client.html.bootstrap.ColumnSize
import org.webscene.client.WebScene as ws

private fun createColumns() = arrayOf(
    ws.Bootstrap.column(ColumnSize.LARGE to 1) { id = "lbl-col" },
    ws.Bootstrap.column(ColumnSize.LARGE to 2) { id = "txt-col" },
    ws.Bootstrap.column(ColumnSize.LARGE to 2) { id = "btn-col" }
)

private fun populateColumns(columns: Array<Column>) {
    val lblColPos = 0
    val txtColPos = 1
    val btnColPos = 2

    columns[lblColPos].children += ws.htmlElement("label") {
        id = "msg-lbl"
        +"Message: "
        attributes["for"] = "msg-txt"
        classes += "text-center"
    }
    columns[txtColPos].children += ws.htmlElement("input") {
        id = "msg-txt"
        attributes["type"] = "text"
        attributes["placeholder"] = "Enter message"
    }
    columns[btnColPos].children += ws.htmlElement("button") {
        id = "show-notification-btn"
        classes += "btn"
        classes += "btn-sm"
        classes += "btn-default"
        attributes["type"] = "submit"
        attributes["value"] = "Show Notification"
        +"Show Notification"
    }
}

internal fun setupWebPage() {
    val columns = createColumns()
    val mainLayout = ws.Bootstrap.container { fullWidth = false }

    populateColumns(columns)
    mainLayout.row {
        classes += "vertical-align"
        columns.forEach { children += it }
    }
    ws.DomEditor.prependElementToBody(mainLayout.toDomElement())
}