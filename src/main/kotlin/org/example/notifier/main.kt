package org.example.notifier

import org.w3c.dom.Element
import org.w3c.notifications.NotificationOptions
import org.webscene.client.WebScene as ws

fun main(args: Array<String>) {
    @Suppress("JoinDeclarationAndAssignment")
    val msgTxt: Element?
    @Suppress("JoinDeclarationAndAssignment")
    val showNotificationBtn: Element?

    setupHomePage()
    msgTxt = ws.DomQuery.elementById("msg-txt")
    showNotificationBtn = ws.DomQuery.elementById("show-notification-btn")
    showNotificationBtn?.addEventListener(type = "click", callback = {
        ws.notification(title = "Test Notification", options = messageNotificationOptions(
                msgTxt.asDynamic().value.toString()))
    })
}

fun messageNotificationOptions(msg: String) = NotificationOptions(body = msg)

private fun setupHomePage() {
    val mainLayout = ws.parentHtmlElement("div") {
        id = "main-layout"
        txtContent = "Message: "
        htmlElement("input") {
            id = "msg-txt"
            attributes["type"] = "text"
            attributes["value"] = "Enter message"
        }
        htmlElement("button") {
            id = "show-notification-btn"
            attributes["type"] = "button"
            attributes["value"] = "Show Notification"
            txtContent = "Show Notification"
        }
    }.toDomElement()

    ws.DomEditor.prependElementToBody(mainLayout)
}