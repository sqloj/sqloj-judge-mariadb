package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSetMetaData
import java.sql.Statement

@Service
class JudgeService
@Autowired constructor(
    val jdbcTemplate: JdbcTemplate
) {
    fun exec(statement: String): List<Any>? {
        System.err.println("[LOG] exec: statement = $statement")
        return jdbcTemplate.execute { stmt: Statement ->
            val rs = stmt.executeQuery(statement)
            val rsmd: ResultSetMetaData = rs.metaData
            val count: Int = rsmd.columnCount
            val list: MutableList<Map<*, *>> =
                ArrayList()
            while (rs.next()) {
                val row: MutableMap<String, Any> = HashMap()
                for (i in 1..count) {
                    row[rsmd.getColumnName(i)] = rs.getObject(i)
                }
                list.add(row)
            }
            list
        }
    }

    fun execWithoutRet(statement: String) {
        System.err.println("[LOG] execWithoutRet: statement = $statement")
        jdbcTemplate.execute(statement)
    }

    fun judge(sql: String, tmpDB: String): Any? {
        execWithoutRet("create database ${tmpDB};")
        execWithoutRet("use ${tmpDB};")
        val list = sql.split(";")
        val ret: MutableList<Any?> = ArrayList()
        try {
            for (l in list) {
                if (l.isBlank()) {
                    continue
                }
                val tmp = exec(l)
                if (tmp != null && tmp.isNotEmpty()) {
                    ret.add(tmp)
                }
            }
        } finally {
            execWithoutRet("drop database ${tmpDB};")
        }
        return ret
    }
}