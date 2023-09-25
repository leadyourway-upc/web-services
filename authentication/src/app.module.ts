import { Module } from '@nestjs/common';
import { UsersModule } from './auth/domain/model/user.module';
import { AuthModule } from './auth/domain/model/auth.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import databaseConfig from './database.config';

@Module({
  imports: [TypeOrmModule.forRoot(databaseConfig), UsersModule, AuthModule],
  controllers: [],
  providers: [],
})
export class AppModule {}
