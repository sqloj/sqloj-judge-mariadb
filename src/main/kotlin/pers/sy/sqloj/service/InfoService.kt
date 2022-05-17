package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pers.sy.sqloj.common.Configs
import pers.sy.sqloj.entity.VersionDO
import pers.sy.sqloj.mapper.DbInfoMapper

@Service
class InfoService
@Autowired constructor(
    val dbInfoMapper: DbInfoMapper
) {
    fun getVersion(): VersionDO {
        return dbInfoMapper.version()
    }

    fun verify(password: String): Boolean {
        return password == Configs.password
    }
}