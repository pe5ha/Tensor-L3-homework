import java.util.ArrayList

interface FileSystemNode {
    fun getParent(): FileSystemNode?
    val name: String
    val path: String
    fun setParent(parent: FileSystemNode?)
}

open class AbstractFileSystemNode(override val name: String, private var parent: FileSystemNode?) :
    FileSystemNode {
    override fun getParent(): FileSystemNode? {
        return parent
    }

    override val path: String
        get() = if (parent == null) name else parent!!.path + "/" + name

    override fun setParent(parent: FileSystemNode?) {
        this.parent = parent
    }
}

class Folder(name: String, parent: FileSystemNode?, vararg _childElements: FileSystemNode) :
    AbstractFileSystemNode(name, parent) {

    val childElements: ArrayList<FileSystemNode> = ArrayList<FileSystemNode>()

    init {
        for (child in _childElements) {
            child.setParent(this)
            childElements.add(child)
        }
        if (parent != null && parent is Folder) parent.addChild(this)
    }

    fun addChild(child: FileSystemNode) {
        childElements.add(child)
    }

    fun textView(i: Int): String { // i - количество табов
        var i = i
        var view = ""
        for (j in 0 until i) view += "\t"
        view += "/ $name\n"
        i++
        for (sn in childElements) {
            if (sn is File) {
                for (j in 0 until i) view += "\t"
                view += """
                    ${sn.name}
                    
                    """.trimIndent()
            } else if (sn is Folder) {
                view += sn.textView(i)
            }
        }
        return view
    }

    override fun toString(): String {
        return textView(0)
    }

}

class File(name: String, parent: FileSystemNode?) : AbstractFileSystemNode(name, parent) {
    init {
        if (parent is Folder) parent.addChild(this)
    }

    val extension: String
        get() = name.split("\\.".toRegex()).toTypedArray()[name.split("\\.".toRegex()).toTypedArray().size - 1]

}