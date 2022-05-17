package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.VersionDO

@Mapper
interface DbInfoMapper {
    fun version(): VersionDO
}